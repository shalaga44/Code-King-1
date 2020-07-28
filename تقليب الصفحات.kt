package main

import kotlin.math.abs

fun main() {
    val totalPages = 10//readLine()!!.toInt()
//    var target = readLine()!!.toInt()
    val halfTotalPages: Int = totalPages / 2
    for (target in 0..5)
        if (target > halfTotalPages)
            println(traverseFromEnd(target, totalPages))
        else
            println(traverseFromStart(target))
}

fun traverseFromStart(target: Int): Int {
    print("$target traverseFromStart ")
    if (target < 2)
        return 0
    return if (isEven(target)) {
        (target) / 2
    } else {
        (target - 1) / 2
    }

}

fun traverseFromEnd(target: Int, totalPages: Int): Int {
    print("$target traverseFromEnd ")
    if (isEven(totalPages)) {
//        if (target == totalPages )
//            return 0
        return if (isEven(target)) {
            (abs(target - totalPages)) / 2
        } else {
            (abs(target - totalPages) +2) / 2
        }
    } else {
//        print("Oh Shit ")
        if (target == totalPages)
            return 0
        return if (isEven(target)) {
            (abs(target - totalPages) ) / 2
        } else {
            (abs(target - totalPages) ) / 2
        }
    }
}

fun isEven(num: Int): Boolean = num % 2 == 0
