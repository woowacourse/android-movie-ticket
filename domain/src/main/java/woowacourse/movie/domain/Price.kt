package woowacourse.movie.domain

class Price(amount: Int = 0) {
    var amount: Int = amount
        private set

    fun plus(cost: Int) {
        amount += cost
    }

    fun minus(cost: Int) {
        amount -= cost
    }
}
