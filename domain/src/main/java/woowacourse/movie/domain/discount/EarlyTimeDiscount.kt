package woowacourse.movie.domain.discount

import woowacourse.movie.domain.PeopleCount
import java.time.LocalDateTime

class EarlyTimeDiscount(
    private val time: LocalDateTime,
    private val count: PeopleCount
) : DiscountPolicy {
    override fun getDiscountPrice(price: Int): Int {
        if (time.hour <= SALE_TIME_STANDARD_MORNING) {
            return price - TICKET_TIME_SALE_AMOUNT * count.count
        }

        return price
    }

    companion object {
        private const val SALE_TIME_STANDARD_MORNING = 11
        private const val TICKET_TIME_SALE_AMOUNT = 2_000
    }
}
