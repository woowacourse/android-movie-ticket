package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val price: Int,
    val date: LocalDateTime,
    val numberOfPeople: Int,
) : Serializable {
    fun calculateTotalPrice(): Int {
        val discountPolicies = listOf(MovieDayDiscountPolicy(), TimeDiscountPolicy())
        val discountedPrice = discountPolicies.fold(price) { ticketPrice, policy ->
            calculateTicketPrice(ticketPrice, policy)
        }
        return discountedPrice * numberOfPeople
    }

    private fun calculateTicketPrice(price: Int, policy: DiscountPolicy): Int {
        if (policy.checkPolicy(date)) return policy.discountPrice(price)
        return price
    }
}
