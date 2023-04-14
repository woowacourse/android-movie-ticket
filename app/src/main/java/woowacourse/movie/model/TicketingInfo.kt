package woowacourse.movie.model

import woowacourse.movie.model.policy.DiscountPolicy
import java.time.LocalDate
import java.time.LocalTime

data class TicketingInfo private constructor(val title: String, val playingDate: LocalDate, val playingTime: LocalTime, val count: Int, val price: Price, val payment: String) : java.io.Serializable {
    companion object {
        fun of(policies: List<DiscountPolicy>, title: String, playingDate: LocalDate, playingTime: LocalTime, count: Int, price: Price, payment: String): TicketingInfo {
            var calculatePrice = price
            for (policy in policies) {
                calculatePrice = policy.calculate(playingDate, playingTime, calculatePrice)
            }
            return TicketingInfo(title, playingDate, playingTime, count, calculatePrice, payment)
        }
    }
}
