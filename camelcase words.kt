fun main() {
    val text = readLine()!!
    var totalWords = 1

    for (s in text) {
        if (!s.isLowerCase())
            totalWords++
    }
    println(totalWords)
}