package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class DateTimeDiscountAdapter(override val dateTime: LocalDateTime) : DateTimeDiscountPolicy {

    override fun discount(price: Money): Money {
        val movieDayPrice = MovieDayTimeDiscountPolicy(dateTime, 0.1).discount(price)
        return timeDiscount(movieDayPrice)
    }

    private fun timeDiscount(price: Money): Money {
        val nextPrice = EarlyBirdTimeDiscountPolicy(dateTime, 2000).discount(price)
        return LateNightTimeDiscountPolicy(dateTime, 2000).discount(nextPrice)
    }
}
