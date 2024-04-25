package woowacourse.movie.domain.pricing

import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.screening.Screening

class UniformPricingSystem(private val defaultPrice: Int = 13000) : PricingSystem {
    override fun calculatePrice(
        screening: Screening,
        quantity: Quantity,
    ): Long {
        return quantity.value * defaultPrice.toLong()
    }
}
