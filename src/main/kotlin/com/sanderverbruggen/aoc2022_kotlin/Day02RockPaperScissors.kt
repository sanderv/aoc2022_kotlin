package com.sanderverbruggen.aoc2022_kotlin

class RockPaperScissors {
    fun countScore(filename: String) {
        val result = readFile(filename).lineSequence()
            .map { line -> scoreLine(findToken(line.first()), findToken(line.last())) }
            .sum()

        println(result)
    }

    fun countCountermoveScore(filename: String) {
        val result = readFile(filename).lineSequence()
            .map { line ->
                val opponent = findToken(line.first())
                val player = findCountermove(opponent, line.last())
                scoreLine(opponent, player)
            }
            .sum()

        println(result)
    }

    fun findCountermove(opponent: RPS, move: Char): RPS {
        return when (move) {
            'X' -> RPS.values().find { !beats(it, opponent) && it != opponent }!!
            'Z' -> RPS.values().find { beats(it, opponent) }!!
            else -> opponent
        }
    }

    fun scoreLine(opponent: RPS, player: RPS): Int {
        val score = player.score + if (beats(player, opponent)) {
            6
        } else if (player == opponent) {
            3
        } else {
            0
        }

        return score
    }

    private fun beats(
        player: RPS,
        opponent: RPS
    ) = (player == RPS.ROCK && opponent == RPS.SCISSORS) ||
            (player == RPS.PAPER && opponent == RPS.ROCK) ||
            (player == RPS.SCISSORS && opponent == RPS.PAPER)

    enum class RPS(val tokens: List<Char>, val score: Int) {
        ROCK(listOf('A', 'X'), 1),
        PAPER(listOf('B', 'Y'), 2),
        SCISSORS(listOf('C', 'Z'), 3)
    }

    fun findToken(token: Char) = RPS.values().find { it.tokens.contains(token) }!!

}

fun main() {
//    RockPaperScissors().countScore("day2_example.txt.txt")
//    RockPaperScissors().countScore("day2_puzzle.txt")

//    RockPaperScissors().countCountermoveScore("day2_example.txt")
    RockPaperScissors().countCountermoveScore("day2_puzzle.txt")
}

