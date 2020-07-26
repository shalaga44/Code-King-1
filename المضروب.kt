fun main() {
    repeatReadInt(readLine()!!.toInt()) {
        println(it.factorial)
    }
}

val Int.factorial: Int
    get() {
        var f = 1
        for (i in 1..this) {
            f *= i
        }
        return (f)
    }

