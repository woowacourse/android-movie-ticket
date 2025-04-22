package woowacourse.movie.domain.service

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.policy.DefaultPricingPolicy
import woowacourse.movie.domain.policy.PricingPolicy
import java.time.LocalDateTime

class MovieTicketService(
    private val pricingPolicy: PricingPolicy = DefaultPricingPolicy(),
) {
    fun createMovieTicket(
        id: Int,
        screeningDateTime: LocalDateTime,
        headCount: Int,
    ): MovieTicket {
        val amount = pricingPolicy.calculatePrice(headCount)
        return MovieTicket(id, screeningDateTime, headCount, amount)
    }
}
