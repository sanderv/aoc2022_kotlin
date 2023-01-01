package com.sanderverbruggen.aoc2022_kotlin


class CathodeRayTube(lines: List<String>) {
    private val cpu = CPU(1, lines)
    private var screen = ""
    private var cycle = 0

    fun solve(): String {
        var busyCycles = 0
        var instruction: CRInstruction? = null

        while (true) {
            if (busyCycles == 0) {
                instruction = cpu.getNextInstruction()
                println(instruction)
                if (instruction == null) {
                    return screen
                }
                busyCycles = instruction.cycles
            }

            updateScreen()
            cycle++
            busyCycles--
            if (busyCycles == 0) {
                instruction?.let { execute(it) }
            }
        }
    }

    private fun updateScreen() {
        var rayPos = cycle % 40
        if (cpu.regX >= (rayPos - 1) && cpu.regX <= (rayPos + 1)) {
            screen += "#"
        } else {
            screen += "."
        }
    }

    private fun execute(instruction: CRInstruction) {
        if (instruction == CRInstruction.ADDX) {
            cpu.regX = cpu.regX + instruction.param
        }
    }
}

private class CPU(var regX: Int, private val lines: List<String>) {
    private var pointer = 0

    fun getNextInstruction(): CRInstruction? {
        if (pointer < lines.size) {
            val parts = lines[pointer].split(" ")

            val instruction = CRInstruction.valueOf(parts.first().uppercase())
            instruction.param = if (parts.size > 1) parts.last().toInt() else 0
            pointer++
            return instruction
        } else {
            return null
        }
    }
}

enum class CRInstruction(val cycles: Int) {
    NOOP(1),
    ADDX(2);

    var param: Int = 0

    override fun toString(): String {
        return "${this.name} " + if (this == ADDX) param.toString() else ""
    }
}

fun main() {
//    val lines = readFile("day10_example.txt").lines()
    val lines = readFile("day10_puzzle.txt").lines()
    val screen = CathodeRayTube(lines).solve()
        .chunked(40)
        .joinToString("\n")
    println(screen)
}
