package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

abstract class DiscountPolicy {
    abstract fun getDiscountPrice(price: Price): Price
    abstract fun isAvailable(date: LocalDate, time: LocalTime): Boolean
    fun calculate(date: LocalDate, time: LocalTime, price: Price): Price {
        if (isAvailable(date, time)) return getDiscountPrice(price)
        return price
    }
}
