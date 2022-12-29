package com.sanderverbruggen.aoc2022_kotlin

import kotlin.math.abs

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
                catchUpTail(direction)
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

    private fun catchUpTail(direction: String) {
        val dx = abs(headPosition.x - tailPosition.x)
        val dy = abs(headPosition.y - tailPosition.y)
        if (dx > 1 || dy > 1) {
            tailPosition = when (direction) {
                "R" -> Position(headPosition.x - 1, headPosition.y)
                "L" -> Position(headPosition.x + 1, headPosition.y)
                "U" -> Position(headPosition.x, headPosition.y + 1)
                "D" -> Position(headPosition.x, headPosition.y - 1)
                else -> Position(headPosition.x, headPosition.y)
            }
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