package woowacourse.movie.domain.discountpolicy

import java.time.LocalDateTime

class DateTimeDiscountAdapter(override val dateTime: LocalDateTime) : DateTimeDiscountPolicy {
    override fun discount(price: Int): Int {
        val movieDayPrice = MovieDayTimeDiscountPolicy(dateTime).discount(price)
        return timeDiscount(movieDayPrice)
    }

    private fun timeDiscount(price: Int): Int {
        val nextPrice = EarlyBirdTimeDiscountPolicy(dateTime).discount(price)
        return LateNightTimeDiscountPolicy(dateTime).discount(nextPrice)
    }
}
