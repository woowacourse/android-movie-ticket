package woowacourse.movie.domain.movie

import woowacourse.movie.domain.seat.Seats

@JvmInline
value class TicketCount private constructor(val value: Int) {
    fun increment(): TicketCount = of(value + 1)

    fun decrement(): TicketCount = of(value - 1)

    fun canIncrement(): Boolean = value < MAX_COUNT

    fun canDecrement(): Boolean = value > MIN_COUNT

    companion object {
        const val MIN_COUNT = 1
        private const val MAX_COUNT = Seats.ROW_SIZE * Seats.COL_SIZE

        fun of(value: Int): TicketCount = TicketCount(value.coerceIn(MIN_COUNT..MAX_COUNT))
    }
}
