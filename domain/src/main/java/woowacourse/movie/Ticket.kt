package woowacourse.movie

import woowacourse.movie.policy.DiscountAdapter
import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) : Serializable {
    fun getPaymentAmount() = DiscountAdapter().getPayment(bookedDateTime) * count
}
