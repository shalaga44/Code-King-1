package main

fun main() {
    val n = readLine()!!.toInt()
    var sum = 0

    repeatReadInt(n) {
        sum += it
    }

    println(sum)
}

inline fun repeatReadInt(times: Int, body: (Int) -> Unit) {
    for (index in 0 until times) {
        body(readLine()!!.toInt())
    }
}