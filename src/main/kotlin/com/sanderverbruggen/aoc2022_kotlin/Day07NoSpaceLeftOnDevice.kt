package com.sanderverbruggen.aoc2022_kotlin

private const val DIR_PREFIX = "dir "
private const val CD_CMD = "$ cd "

class Day07NoSpaceLeftOnDevice {
    val root = Directory("root")

    fun solve(filename: String): Long {
        var currentDir = root
        readFile(filename).lines()
            .map { line ->
                if (line.startsWith(CD_CMD)) {
                    currentDir = currentDir.take(line.drop(CD_CMD.length))
                } else if (line.matches(Regex("[0-9]+.*+"))) {
                    currentDir.addFile(line)
                } else if (line.startsWith(DIR_PREFIX)) {
                    currentDir.take(line.drop(DIR_PREFIX.length))
                }
            }
        val allDirs = root.findAllDirs()

//        return part1Solution(allDirs)
        return part2Solution(allDirs)
    }

    private fun part1Solution(allDirs: List<Directory>) =
        allDirs
            .map { it.totalSize() }
            .filter { it < 100_000 }
            .sum()

    private fun part2Solution(allDirs: List<Directory>): Long {
        val filesystemSize = 70_000_000
        val sizeNeeded = 30_000_000
        val freeSpace = filesystemSize - root.totalSize()

        val candidates = allDirs.filter { freeSpace + it.totalSize() >= sizeNeeded }
        return candidates
            .map { it.totalSize() }
            .sorted()
            .first()
    }

    private fun readFileLines(currentDir: Directory) {
        TODO("Not yet implemented")
    }
}

class Directory(val name: String, val parent: Directory? = null) {
    val files = mutableMapOf<String, Long>()
    val dirs = mutableListOf<Directory>()

    fun take(dirName: String): Directory {
        if (dirName == ".." && parent != null) {
            return parent
        } else if (dirName == "/") {
            return root()
        }

        var result = dirs.find { it.name == dirName }
        if (result == null) {
            result = Directory(dirName, this)
            dirs.add(result)
        }

        return result
    }

    fun addFile(fileLine: String) {
        val split = fileLine.split(" ")
        files[split[1]] = split[0].toLong()
    }

    fun totalSize(): Long {
        val dirsSize = dirs.map { it.totalSize() }
            .sum()
        val filesSize = files.values.sum()

        return dirsSize + filesSize
    }

    fun findAllDirs(): List<Directory> {
        val result = mutableListOf<Directory>()
        result.addAll(dirs)
        result.addAll(dirs.flatMap { it.findAllDirs() })
        return result
    }

    fun root(): Directory = parent?.root() ?: this
}

fun main() {
    val result = Day07NoSpaceLeftOnDevice().solve("day7_puzzle.txt")

    println(result)
}