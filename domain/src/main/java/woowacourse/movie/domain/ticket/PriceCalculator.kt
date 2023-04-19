package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.policy.DiscountPolicy
import java.time.LocalDate
import java.time.LocalTime

class PriceCalculator(private val polices: List<DiscountPolicy>, private val defaultPrice: Price = Price()) {
    fun calculate(playingDate: LocalDate, playingTime: LocalTime, count: Int): Price {
        var calculatePrice = defaultPrice
        for (policy in polices) {
            calculatePrice = policy.calculate(playingDate, playingTime, calculatePrice)
        }
        return Price(calculatePrice.price * count)
    }
}
