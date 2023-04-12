package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

class MovieDayPolicy : DiscountPolicy() {
    override fun getDiscountPrice(price: Price): Price {
        return Price((price.price * 0.9).toInt())
    }

    override fun isAvailable(date: LocalDate, time: LocalTime): Boolean {
        return date.dayOfMonth == 10 || date.dayOfMonth == 20 || date.dayOfMonth == 30
    }
}
