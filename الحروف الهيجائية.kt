package main

fun main() {
    val letters = Array(26) { _ -> 0 }
    val text = readLine()!!.toString()
    for (s in text) {
        if (s.isLetter()) {
            letters[s.toLowerCase().toInt() - 97] = 1
        }
    }
    println(
        when (letters.sum()) {
            26 -> "yes"
            else -> "no"
        }
    )
}