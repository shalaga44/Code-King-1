import java.util.Scanner

fun main() {
    val times = readLine()!!.toInt()

    repeatReadLenThenListInt(times) head@{
        if (it.size == 1) {
            println("YES")
            return@head
        } else
            for (idx in 1..it.lastIndex) {
                val leftArray = it.copyOfRange(0, idx).toMutableList()
                val rightArray = it.copyOfRange(idx + 1, it.lastIndex + 1).toMutableList()
                if (rightArray.sum() == leftArray.sum()) {
                    println("YES")
                    return@head

                }
            }
        println("NO")
    }


}

inline fun repeatReadLenThenListInt(times: Int, action: (Array<Int>) -> Unit) {
    val input = Scanner(System.`in`)

    for (index in 0 until times) {
        val length = input.nextInt()
        var arrayInt = Array<Int>(length) { input.nextInt() }
        action(arrayInt)

    }
}
