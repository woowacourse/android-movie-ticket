package woowacourse.movie.model.pricing

import woowacourse.movie.model.Quantity
import woowacourse.movie.model.screening.Screening

class UniformPricingSystem(private val defaultPrice: Int = 13000) : PricingSystem {
    override fun calculatePrice(
        screening: Screening,
        quantity: Quantity,
    ): Long {
        return quantity.value * defaultPrice.toLong()
    }
}
