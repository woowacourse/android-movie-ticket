package woowacourse.movie.domain.discount

@JvmInline
value class Money(val amount: Int) : Comparable<Money> {
    init {
        require(amount.isNotNegative()) { NEGATIVE_ERROR }
    }

    private fun Int.isNotNegative(): Boolean = this >= 0

    operator fun plus(money: Money): Money = Money(amount + money.amount)

    operator fun minus(money: Money): Money = Money(amount - money.amount)

    operator fun div(number: Int): Money = Money(amount / number)

    operator fun times(number: Int): Money = Money(amount * number)

    override fun compareTo(other: Money): Int = this.amount.compareTo(other.amount)

    companion object {
        private const val NEGATIVE_ERROR = "[ERROR] 금액은 음수일 수 없습니다."
    }
}
