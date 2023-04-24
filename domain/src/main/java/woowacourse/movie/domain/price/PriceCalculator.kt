package woowacourse.movie.domain.price

import woowacourse.movie.domain.policy.DiscountPolicy
import java.time.LocalDateTime

class PriceCalculator(
    private val policies: List<DiscountPolicy>,

) {

    fun calculate(price: Price, playingDateTime: LocalDateTime): Price {
        val calculatePrice = policies.fold(price) { total, policy ->
            policy.calculate(
                playingDateTime.toLocalDate(),
                playingDateTime.toLocalTime(),
                total,
            )
        }
        return Price(calculatePrice.price)
    }
}
