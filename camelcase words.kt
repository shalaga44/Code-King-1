package main

fun main() {
    val text = readLine()!!
    var totalWords = 1

    text.forEach { letter ->
        if (letter.isUpperCase())
            totalWords++
    }
    println(totalWords)
}
