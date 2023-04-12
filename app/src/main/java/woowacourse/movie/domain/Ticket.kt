package woowacourse.movie.domain

import java.io.Serializable

@JvmInline
value class Ticket(val count: Int = MIN_TICKET_COUNT) : Serializable {
    init {
        require(count >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    fun calculateTotalPrice(ticketPrice: Int = DEFAULT_TICKET_PRICE) =
        count * ticketPrice

    fun calculateTotalPrice2(
        ticketPrice: Int = DEFAULT_TICKET_PRICE,
        movieDate: MovieDate,
        movieTime: MovieTime
    ): Int {
        var discountedTicketPrice = ticketPrice
        if (movieDate.isDiscountDay()) discountedTicketPrice =
            (ticketPrice - ticketPrice * DISCOUNT_PERCENT).toInt()
        if (movieTime.isDiscountTime()) discountedTicketPrice -= DISCOUNT_PRICE

        return discountedTicketPrice * count
    }

    operator fun dec(): Ticket = if (count > MIN_TICKET_COUNT) {
        Ticket(count - TICKET_UP_DOWN_UNIT)
    } else {
        this
    }

    operator fun inc(): Ticket = Ticket(count + TICKET_UP_DOWN_UNIT)

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val TICKET_UP_DOWN_UNIT = 1
        private const val DEFAULT_TICKET_PRICE = 13_000
        private const val DISCOUNT_PRICE = 2_000
        private const val DISCOUNT_PERCENT = 0.1

        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 최소 1장 이상이어야 합니다."
    }
}
