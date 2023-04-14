package domain.reservation

import domain.discount.MovieDiscountEvent
import domain.movie.Movie
import domain.payment.PaymentAmount
import domain.payment.PaymentType
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
        private const val TICKET_PRICE = 13000

        fun from(movie: Movie, ticketCount: Int, screeningDateTime: LocalDateTime): Reservation {
            val paymentAmount: PaymentAmount = MovieDiscountEvent().discount(
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
