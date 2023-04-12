package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val price: Int,
    val date: LocalDateTime,
    val movieTitle: String,
    val numberOfPeople: Int,
) : Serializable {

    fun calculateTotalPrice(): Int {
        var a = price
        a = calculateTicketPrice(a, MovieDayDiscountPolicy())
        a = calculateTicketPrice(a, TimeDiscountPolicy())
        return a * numberOfPeople
    }

    private fun calculateTicketPrice(price: Int, policy: DiscountPolicy): Int {
        if (policy.checkPolicy(date)) return policy.discountPrice(price)
        return price
    }
}
