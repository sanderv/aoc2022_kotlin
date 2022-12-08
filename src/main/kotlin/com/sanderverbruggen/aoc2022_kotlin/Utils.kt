package com.sanderverbruggen.aoc2022_kotlin

fun readFile(filename: String) = Dummy::class.java.classLoader.getResource(filename).readText()

class Dummy
