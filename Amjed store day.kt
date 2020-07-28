package main

class AmjedStoreDay
constructor(
    private var moviePrice: Int,
    private var discountPrice: Int,
    private var minimalDiscountLimit: Int
) {
    private var canDiscountMore = true
    private var isFirstTime = true
    fun canBuyMovie(money: Int): Boolean {
        return money >= moviePrice
    }

    fun buyMovie(money: Int): Int {
        if (isFirstTime) {
            isFirstTime = false
            return money - moviePrice
        } else
            if (canDiscountMore)
                discountThePrice()
        return money - moviePrice


    }

    private fun discountThePrice() {
        when {
            isCanDiscountMore() -> discount()
            else -> canDiscountMore = false
        }
    }

    private fun isPriceBiggerThanLimit(): Boolean {
        return moviePrice > minimalDiscountLimit
    }

    private fun discount() {
        moviePrice -= discountPrice
    }

    private fun isCanDiscountMore(): Boolean {
        return when {
            isMinimalDiscount() -> false
            isPriceBiggerThanLimit() -> moviePrice - discountPrice >= minimalDiscountLimit
            else -> false
        }
    }

    private fun isMinimalDiscount(): Boolean {
        return moviePrice == minimalDiscountLimit

    }
}

fun main() {
    val p = readLine()!!.trim().toInt()
    val d = readLine()!!.trim().toInt()
    val m = readLine()!!.trim().toInt()

    var myMoney = readLine()!!.trim().toInt()
    var myMovies = 0

    val store = AmjedStoreDay(p, d, m)

    while (store.canBuyMovie(myMoney)) {
        myMoney = store.buyMovie(myMoney)
        myMovies++
    }
    print(myMovies)
}



