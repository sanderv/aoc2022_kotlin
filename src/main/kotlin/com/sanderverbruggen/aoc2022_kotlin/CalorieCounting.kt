package com.sanderverbruggen.aoc2022_kotlin

class CalorieCounting {

    fun maxCalories(filename: String) {
        val result = readFile(filename).splitToSequence("\n\n")
        val elfCals = result.map { elf ->
            elf.lines()
                .filter { cal -> cal.isNotBlank() }
                .map { cal -> cal.toInt() }
                .sum()
        }

        println(elfCals.max())
    }
}

fun main() {
    solvePuzzle()
}

fun solveExample() {
    CalorieCounting().maxCalories("day1_example.txt")
}

fun solvePuzzle() {
    CalorieCounting().maxCalories("day1_puzzle.txt")
}
