package com.sanderverbruggen.aoc2022_kotlin

class CathodeRayTube {
    private val cpu = CPU(1)
    fun solve(lines: List<String>): Int {
        var pointer = 0
        val signalStrength = mutableListOf<Int>(1)

        while (pointer < lines.size) {
            val parts = lines[pointer].split(" ")
            val opcode = parts.first()
            val param = if (parts.size > 1) parts.last().toInt() else 0

            val instr = CRInstruction.valueOf(opcode.uppercase())
            repeat(instr.cycles) {
                signalStrength.add(cpu.regX)
                println("${signalStrength.size}: ${cpu.regX}")
            }
            if (instr == CRInstruction.ADDX) {
                cpu.regX = cpu.regX + param
                println("$pointer: add $param -> ${cpu.regX}")
            }
            pointer++
        }

        return listOf(20, 60, 100, 140, 180, 220)
            .map { i -> i * signalStrength[i] }
            .also { println(it) }
            .sum()
    }
}

private class CPU(var regX: Int)

enum class CRInstruction(val cycles: Int) {
    NOOP(1),
    ADDX(2)
}

fun main() {
//    val lines = readFile("day10_example.txt").lines()
    val lines = readFile("day10_puzzle.txt").lines()
    println(CathodeRayTube().solve(lines))
}
