package woowacourse.movie.model

import woowacourse.movie.model.policy.MorningPolicy
import woowacourse.movie.model.policy.MovieDayPolicy
import woowacourse.movie.model.policy.NightPolicy
import java.time.LocalDate
import java.time.LocalTime

data class TicketingInfo private constructor(val title: String, val playingDate: LocalDate, val playingTime: LocalTime, val count: Int, val price: Price, val payment: Payment) : java.io.Serializable {
    companion object {
        private val policies = listOf(MovieDayPolicy(), MorningPolicy(), NightPolicy())
        fun of(title: String, playingDate: LocalDate, playingTime: LocalTime, count: Int, price: Price, payment: Payment): TicketingInfo {
            var calculatePrice = price
            for (policy in policies) {
                calculatePrice = policy.calculate(playingDate, playingTime, calculatePrice)
            }
            return TicketingInfo(title, playingDate, playingTime, count, calculatePrice, payment)
        }
    }
}
