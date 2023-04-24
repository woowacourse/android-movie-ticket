package woowacourse.movie.domain.model.seat

import woowacourse.movie.domain.model.movie.TicketPrice

typealias DomainSeatClass = SeatClass

enum class SeatClass(val ticketPrice: TicketPrice) {
    S(TicketPrice(15_000)),
    A(TicketPrice(12_000)),
    B(TicketPrice(10_000));

    companion object {
        private const val INVALID_ROW_EXCEPTION_MESSAGE = "올바르지 않은 행입니다."

        fun get(row: Int): SeatClass = when (row) {
            in 1..2 -> B
            in 3..4 -> S
            5 -> A
            else -> throw IllegalArgumentException(INVALID_ROW_EXCEPTION_MESSAGE)
        }
    }
}
