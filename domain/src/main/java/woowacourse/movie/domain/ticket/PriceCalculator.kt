package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.policy.DiscountPolicy
import java.time.LocalDate
import java.time.LocalTime

class PriceCalculator(
    private val policies: List<DiscountPolicy>,
    private val defaultPrice: Price = Price()
) {
    fun calculate(playingDate: LocalDate, playingTime: LocalTime, count: Int): Price {
        val calculatePrice = policies.fold(defaultPrice) { total, policy ->
            policy.calculate(
                playingDate,
                playingTime,
                total
            )
        }
        return Price(calculatePrice.price * count)
    }
}
