package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.policy.DiscountPolicy
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Ticket private constructor(
    val title: String,
    val playingDateTime: LocalDateTime,
    val count: Int,
    val seats: List<String>,
    val price: Price
) {
    companion object {
        fun of(
            policies: List<DiscountPolicy>,
            title: String,
            playingDate: LocalDate,
            playingTime: LocalTime,
            count: Int,
            seats: List<String>,
            price: Price
        ): Ticket {
            val calculatedPrice = PriceCalculator(policies, price).calculate(playingDate, playingTime, count)
            val mergeDate = LocalDateTime.of(playingDate, playingTime)
            return Ticket(title, mergeDate, count, seats, calculatedPrice)
        }
    }
}
