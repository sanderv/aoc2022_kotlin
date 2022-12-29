package com.sanderverbruggen.aoc2022_kotlin

import kotlin.math.abs
import kotlin.math.sign

class RopeBridge {
    private val visited = mutableSetOf<Position>()
    private val knots = MutableList<Position>(10) { _ -> Position(0, 0) }
    fun solvePart1(lines: List<String>): Int {
        lines.forEach { line ->
            val command = line.split(" ")
            val direction = command[0]
            val steps = command[1].toInt()
            visited.add(Position(knots.last().x, knots.last().y))
            repeat(steps) {
                moveHead(direction)
                catchUpTail()
                visited.add(Position(knots.last().x, knots.last().y))
            }

        }
        return visited.size
    }

    private fun moveHead(direction: String) {
        var x = knots.first().x
        var y = knots.first().y
        when (direction) {
            "R" -> x++
            "L" -> x--
            "U" -> y--
            "D" -> y++
        }
        knots[0] = Position(x, y)
    }

    private fun catchUpTail() {
        knots.forEachIndexed { i, _ ->
            catchUp(i)
        }
    }

    private fun catchUp(pos: Int) {
        if (pos < knots.size - 1) {
            val thisKnot = knots[pos]
            val nextKnot = knots[pos + 1]
            val dx = thisKnot.x - nextKnot.x
            val dy = thisKnot.y - nextKnot.y
            if (abs(dx) > 1 || abs(dy) > 1) {
                knots[pos + 1] = Position(knots[pos + 1].x + dx.sign, knots[pos + 1].y + dy.sign)
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
//    val lines = readFile("day9_example2.txt").lines()
    val lines = readFile("day9_puzzle.txt").lines()

    println(RopeBridge().solvePart1(lines))
}