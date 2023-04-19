package payment

import discount.Discount
import discount.DiscountRule
import discount.EarlyNightDiscount
import discount.MovieDayDiscount
import movie.Movie
import java.io.Serializable
import java.time.LocalDateTime

data class Reservation(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {

        private const val TICKET_PRICE = 13_000

        fun from(movie: Movie, ticketCount: Int, screeningDateTime: LocalDateTime): Reservation {
            val discount: DiscountRule = Discount(EarlyNightDiscount(), MovieDayDiscount())
            val paymentAmount: PaymentAmount = discount.getPaymentAmountResult(
                PaymentAmount(ticketCount * TICKET_PRICE),
                screeningDateTime
            )
            return Reservation(
                movie = movie,
                screeningDateTime = screeningDateTime,
                ticketCount = ticketCount,
                paymentAmount = paymentAmount,
            )
        }
    }
}
