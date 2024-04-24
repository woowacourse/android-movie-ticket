package woowacourse.movie.domain.pricing

import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.screening.Screening

interface PricingSystem {
    fun calculatePrice(
        screening: Screening,
        quantity: Quantity,
    ): Long
}
