package woowacourse.movie.domain

@JvmInline
value class TicketCount(val count: Int) {
    init {
        require(count >= MINIMUM_COUNT) { INVALID_COUNT }
    }

    fun canMinus(): Boolean = count > MINIMUM_COUNT

    operator fun plus(value: Int): TicketCount = TicketCount(count + value)

    operator fun minus(value: Int): TicketCount = TicketCount(count - value)

    companion object {
        private const val INVALID_COUNT = "예약 개수는 1보다 같거나 커야 합니다."
        private const val MINIMUM_COUNT = 1
    }
}
