package com.sanderverbruggen.aoc2022_kotlin

class TreetopTreeHouse {
}

class Forest(lines: List<String>) {
    val maxX = lines[0].length - 1
    val maxY = lines.size - 1
    val forest: Array<Array<Int>> = lines
        .map { line ->
            line
                .map { it.digitToInt() }
                .toTypedArray()
        }
        .toTypedArray()

    fun countVisibleTrees(): Int {
        return forest.flatMapIndexed { y, row ->
            row
                .mapIndexed { x, _ -> isTreeVisible(x, y) }
        }.count { it }

    }

    private fun isTreeVisible(x: Int, y: Int): Boolean {
        return isBorder(x, y) || isTreeVisibleHor(x, y) || isTreeVisibleVer(x, y)
    }

    private fun isBorder(x: Int, y: Int) = x == 0 || y == 0 || x == maxX || y == maxY

    private fun isTreeVisibleHor(x: Int, y: Int): Boolean {
        val treeLine = forest[y]
        return isVisible(treeLine, x);
    }

    private fun isTreeVisibleVer(x: Int, y: Int): Boolean {
        val treeLine = forest
            .map { it[x] }
            .toTypedArray()
        return isVisible(treeLine, y)
    }

    private fun isVisible(treeLine: Array<Int>, pos: Int): Boolean {
        val treeHeight = treeLine[pos]
        var hidden = treeLine.take(pos).filter { it >= treeHeight }.any()
        hidden = hidden && treeLine.drop(pos + 1).filter { it >= treeHeight }.any()
        return !hidden
    }

    fun findMaxScenicTree(): Int {
        return forest.flatMapIndexed() { y, row ->
            row
                .mapIndexed { x, _ -> scenicScore(x, y) }
        }.max()
    }

    private fun scenicScore(x: Int, y: Int): Int {
        val scoreNorth = scenicScore(x, y, dy = -1)
        val scoreSouth = scenicScore(x, y, dy = 1)
        val scoreEast = scenicScore(x, y, dx = 1)
        val scoreWest = scenicScore(x, y, dx = -1)
        val score = scoreNorth * scoreSouth * scoreEast * scoreWest
        println("\t($x, $y) = $score")
        return score
    }

    private fun scenicScore(x: Int, y: Int, dx: Int = 0, dy: Int = 0): Int {
        if (isBorder(x, y))
            return 0;
        var score = 0
        val viewHeight = forest[y][x]
        var currentX = x + dx;
        var currentY = y + dy;
        while (inForest(currentX, currentY)) {
            score++
            if (forest[currentY][currentX] >= viewHeight) {
                return score
            }
            currentX += dx
            currentY += dy
        }
//        println("($x, $y) = $score")
        return score
    }

    private fun inForest(x: Int, y: Int): Boolean = x >= 0 && y >= 0 && x <= maxX && y <= maxY
}


fun main() {
//    val forest = Forest(readFile("day8_example.txt").lines())
    val forest = Forest(readFile("day8_puzzle.txt").lines())
    println(forest.countVisibleTrees())
    println(forest.findMaxScenicTree())
}