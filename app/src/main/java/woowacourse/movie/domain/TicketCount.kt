package woowacourse.movie.domain

@JvmInline
value class TicketCount private constructor(val value: Int) {
    fun increment(): TicketCount = of(value + 1)

    fun decrement(): TicketCount = of(value - 1)

    companion object {
        const val MIN_COUNT = 1

        fun of(value: Int): TicketCount {
            return if (value >= MIN_COUNT) {
                TicketCount(value)
            } else {
                TicketCount(MIN_COUNT)
            }
        }
    }
}
