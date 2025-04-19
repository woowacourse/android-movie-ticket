package woowacourse.movie.model.data

import woowacourse.movie.model.policy.PricingPolicy
import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    private val pricingPolicy: PricingPolicy,
) : Serializable {
    val amount: Int = pricingPolicy.calculatePrice(headCount)
}
