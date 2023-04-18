package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.policy.DiscountPolicy
import java.time.LocalDate
import java.time.LocalTime

data class Ticket private constructor(val title: String, val playingDate: LocalDate, val playingTime: LocalTime, val count: Int, val price: Price) {
    companion object {
        fun of(policies: List<DiscountPolicy>, title: String, playingDate: LocalDate, playingTime: LocalTime, count: Int, price: Price): Ticket {
            var calculatePrice = price
            for (policy in policies) {
                calculatePrice = policy.calculate(playingDate, playingTime, calculatePrice)
            }
            return Ticket(title, playingDate, playingTime, count, calculatePrice)
        }
    }
}
