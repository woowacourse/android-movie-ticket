package woowacourse.movie.domain.model

data class Money(val value: Int) {

    init {
        require(value >= MIN_MONEY)
    }

    fun reduceMoneyWithRate(rate: Double) = Money(value - (value * rate).toInt())

    fun reduceMoneyWithAmount(amount: Int) = Money(value - amount)

    fun multiplyMoneyWithCount(count: Int) = Money(value * count)

    companion object {
        private const val MIN_MONEY = 0
    }
}
