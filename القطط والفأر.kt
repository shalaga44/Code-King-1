package main

import kotlin.math.abs


fun main() {
    val a: Int = readLine()!!.toInt()
    val b: Int = readLine()!!.toInt()
    val c: Int = readLine()!!.toInt()

    val aFromC = abs(a - c)
    val bFromC = abs(b - c)

    when {
        aFromC < bFromC -> println("A")
        aFromC > bFromC -> println("B")
        else -> println("C")
    }
}