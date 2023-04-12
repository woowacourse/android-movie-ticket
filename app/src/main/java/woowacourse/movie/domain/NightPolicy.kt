package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

class NightPolicy : DiscountPolicy() {
    override fun getDiscountPrice(price: Price): Price {
        return Price(price.price - 2000)
    }

    override fun isAvailable(date: LocalDate, time: LocalTime): Boolean {
        return time.hour >= 20
    }
}
