package main

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun main() {
    println(eval(readLine()!!))
}

fun eval(expr: String): Int {
    val nums = mutableListOf<Int>()
    var opr = ""
    for (s in expr) {
        if (s.isDigit())
            nums.add(s.toString().toInt())
        else opr = s.toString()
    }
    when (opr) {
        "+" -> return nums[0] + nums[1]
        "-" -> return nums[0] - nums[1]
    }

    return 0

}

fun String.runCommand(
    workingDir: File = File("."),
    timeoutAmount: Long = 60,
    timeoutUnit: TimeUnit = TimeUnit.SECONDS
): String? = try {
    ProcessBuilder(split("\\s".toRegex()))
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start().apply { waitFor(timeoutAmount, timeoutUnit) }
        .inputStream.bufferedReader().readText()
} catch (e: IOException) {
    e.printStackTrace()
    null
}