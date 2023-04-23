package woowacourse.movie.domain

@JvmInline
value class Money(val amount: Int) {
    init {
        require(amount.isNotNegative()) { NEGATIVE_ERROR }
    }

    private fun Int.isNotNegative(): Boolean = this >= 0

    operator fun minus(money: Money): Money = Money(amount - money.amount)

    operator fun div(i: Int): Money = Money(amount / i)

    companion object {
        private const val NEGATIVE_ERROR = "[ERROR] 금액은 음수일 수 없습니다."
    }
}
