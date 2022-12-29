package com.sanderverbruggen.aoc2022_kotlin

import kotlin.math.abs
import kotlin.math.sign

class RopeBridge {
    private val visited = mutableSetOf<Position>()
    private var headPosition = Position(0, 0)
    private var tailPosition = Position(0, 0)
    fun solvePart1(lines: List<String>): Int {
        lines.forEach { line ->
            val command = line.split(" ")
            val direction = command[0]
            val steps = command[1].toInt()
            visited.add(tailPosition)
            repeat(steps) {
                moveHead(direction)
                catchUpTail()
                visited.add(tailPosition)
            }

        }
        return visited.size
    }

    private fun moveHead(direction: String) {
        var x = headPosition.x
        var y = headPosition.y
        when (direction) {
            "R" -> x++
            "L" -> x--
            "U" -> y--
            "D" -> y++
        }
        headPosition = Position(x, y)
    }

    private fun catchUpTail() {
        val dx = headPosition.x - tailPosition.x
        val dy = headPosition.y - tailPosition.y
        if (abs(dx) > 1 || abs(dy) > 1) {
            tailPosition = Position(tailPosition.x + dx.sign, tailPosition.y + dy.sign)
        }
    }
}

class Position(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "($x, $y)"
    }

}

fun main() {
//    val lines = readFile("day9_example.txt").lines()
    val lines = readFile("day9_puzzle.txt").lines()

    println(RopeBridge().solvePart1(lines))
}