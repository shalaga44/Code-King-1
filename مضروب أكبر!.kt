import java.math.BigInteger

fun main() {
    val num = readLine()!!.toInt()
    println(num.bigFactorial)

}

val Int.bigFactorial: BigInteger
    get() {
        var factorial = BigInteger.ONE
        for (i in 1..this) {
            factorial = factorial
                .multiply(BigInteger.valueOf(i.toLong()))
        }
        return (factorial)
    }

