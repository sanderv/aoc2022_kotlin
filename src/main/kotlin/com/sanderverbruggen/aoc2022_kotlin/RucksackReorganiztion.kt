package com.sanderverbruggen.aoc2022_kotlin

class RucksackReorganiztion {
    fun solvePart1(filename: String): Int {
        return readFile(filename).lineSequence()
            .map { line ->
                val halfLength = line.length / 2
                Pair(line.substring(0, halfLength), line.substring(halfLength))
            }
            .map { findCommonChar(it.first, it.second) }
            .also { println(it.toList()) }
            .filterNotNull()
            .map { getPrio(it) }
            .also { println(it.toList()) }
            .sum()
    }

    fun solvePart2(filename: String): Int {
        return readFile(filename).lines()
            .chunked(3)
            .map { findCommonChar(it) }
            .also { println(it.toList()) }
            .filterNotNull()
            .map { getPrio(it) }
            .sum()
    }

    private fun findCommonChar(first: String, second: String): Char? {
        return first.find { second.contains(it) }
    }

    private fun findCommonChar(strings: List<String>): Char? {
        val first = strings[0]
        val second = strings[1]
        val third = strings[2]

        return first.find { second.contains(it) && third.contains(it) }
    }

    private fun getPrio(char: Char): Int = if (char.isLowerCase()) {
        char.code - 'a'.code + 1
    } else {
        char.code - 'A'.code + 27
    }
}

fun main() {
//    val result = RucksackReorganiztion().solvePart1("day3_example.txt")
//    val result = RucksackReorganiztion().solvePart1("day3_puzzle.txt")

//    val result = RucksackReorganiztion().solvePart2("day3_example.txt")
    val result = RucksackReorganiztion().solvePart2("day3_puzzle.txt")

    println(result)
}