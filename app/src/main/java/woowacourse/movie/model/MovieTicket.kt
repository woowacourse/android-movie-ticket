package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    private val pricingPolicy: PricingPolicy
) : Serializable {
    val amount: Int = pricingPolicy.calculatePrice(headCount)
}
