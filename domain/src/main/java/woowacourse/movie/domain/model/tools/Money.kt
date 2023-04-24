package woowacourse.movie.domain.model.tools

data class Money(val value: Int) {

    init {
        require(value >= MIN_MONEY)
    }

    operator fun minus(amount: Int) = Money(value - amount)
    operator fun minus(otherMoney: Money) = Money(value - otherMoney.value)
    operator fun times(amount: Int) = Money((value * amount))
    operator fun times(amount: Double) = Money((value * amount).toInt())

    companion object {
        private const val MIN_MONEY = 0
    }
}
