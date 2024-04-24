package woowacourse.movie.model.pricing

import woowacourse.movie.model.Quantity
import woowacourse.movie.model.screening.Screening

interface PricingSystem {
    fun calculatePrice(
        screening: Screening,
        quantity: Quantity,
    ): Long
}
