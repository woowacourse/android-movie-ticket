package woowacourse.movie.domain.discount

import woowacourse.movie.domain.PeopleCount
import java.time.LocalDateTime

class LateTimeDiscount(
    private val time: LocalDateTime,
    private val count: PeopleCount
) : DiscountPolicy {
    override fun getDiscountPrice(price: Int): Int {
        if (time.hour >= SALE_TIME_STANDARD_NIGHT) {
            return price - TICKET_TIME_SALE_AMOUNT * count.count
        }

        return price
    }

    companion object {
        private const val SALE_TIME_STANDARD_NIGHT = 20
        private const val TICKET_TIME_SALE_AMOUNT = 2_000
    }
}
