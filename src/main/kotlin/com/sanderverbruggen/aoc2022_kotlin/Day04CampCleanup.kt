package com.sanderverbruggen.aoc2022_kotlin

class CampCleanup {
    fun solve(filename: String): Int {
        return readFile(filename).lines()
            .map { splitIntoRanges(it) }
            .filter { ranges ->
                val intersection = ranges.first.intersect(ranges.second)
//                intersection == ranges.first || intersection == ranges.second // part 1
                intersection.isNotEmpty() // part 2
            }
            .count()
    }

    private fun splitIntoRanges(line: String): Pair<Set<Int>, Set<Int>> {
        val ranges = line.split(',')
            .map { rangeString ->
                val numbers = rangeString.split('-')
                IntRange(numbers[0].toInt(), numbers[1].toInt()).toSet()
            }

        return Pair(ranges[0], ranges[1])
    }
}

fun main() {
//    val result = CampCleanup().solve("day4_example.txt")
    val result = CampCleanup().solve("day4_puzzle.txt")

    println(result)
}

