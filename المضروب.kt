package main

fun main() {
    repeatReadInt(readLine()!!.toInt()) {
        println(it.factorial)
    }
}

val Int.factorial: Int
    get() {
        var f = 1
        (1..this).forEach { i ->
            f *= i
        }
        return (f)
    }

