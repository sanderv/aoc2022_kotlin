package com.sanderverbruggen.aoc2022_kotlin

class SupplyStacks {
    fun solve(filename: String): String {
        val lines = readFile(filename).lines()
        val indexLineNumber = findIndexLineNumber(lines)
        val indexes = lines[indexLineNumber]
        val stacks = parseStacks(indexes, lines.take(indexLineNumber))
        val instructions = parsInstructions(lines.drop(indexLineNumber + 2))

        executeInstructions(instructions, stacks)

        return stacks
            .map { it.last() }
            .joinToString(separator = "")
    }

    private fun parsInstructions(lines: List<String>): List<Instruction> {
        val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
        return lines
            .map { line -> regex.find(line) }
            .map { matchResult ->
                val (count, from, to) = matchResult!!.destructured
                Instruction(count.toInt(), from.toInt() - 1, to.toInt() - 1)
            }
    }

    private fun findIndexLineNumber(lines: List<String>): Int {
        val found = lines.find { it.replace(" ", "").toDoubleOrNull() != null }
        return lines.indexOf(found)
    }

    private fun parseStacks(
        indexLine: String,
        lines: List<String>
    ): MutableList<MutableList<String>> {
        val reversedLines = lines.reversed()
        return indexLine.splitToSequence(Regex("\\s+"))
            .filter { it.isNotBlank() }
            .map { stackNr ->
                val index = indexLine.indexOf(stackNr)
                gatherStack(reversedLines, index)
            }
            .toMutableList()
    }

    private fun gatherStack(lines: List<String>, index: Int): MutableList<String> {
        return lines
            .map {
                if (it.length >= index) {
                    it.substring(index, index + 1)
                } else {
                    ""
                }
            }
            .filter { it.isNotBlank() }
            .toMutableList()
    }

    private fun executeInstructions(
        instructions: List<Instruction>,
        stacks: MutableList<MutableList<String>>
    ) {
        instructions.forEach { instruction ->
            val crane = stacks[instruction.from].takeLast(instruction.count)
            stacks[instruction.from] =
                stacks[instruction.from].dropLast(instruction.count).toMutableList()
            stacks[instruction.to].addAll(crane.reversed())
        }
    }
}

class Instruction(val count: Int, val from: Int, val to: Int)

fun main() {
//    val result = SupplyStacks().solve("day5_example.txt")
    val result = SupplyStacks().solve("day5_puzzle.txt")

    println(result)
}