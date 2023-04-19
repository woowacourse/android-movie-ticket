package woowacourse.movie.domain.discount

import java.time.LocalDateTime

class EarlyTimeDiscount(
    private val time: LocalDateTime
) : DiscountPolicy {
    override fun getDiscountPrice(price: Int): Int {
        if (time.hour <= SALE_TIME_STANDARD_MORNING) {
            return price - TICKET_TIME_SALE_AMOUNT
        }

        return price
    }

    companion object {
        private const val SALE_TIME_STANDARD_MORNING = 11
        private const val TICKET_TIME_SALE_AMOUNT = 2_000
    }
}
