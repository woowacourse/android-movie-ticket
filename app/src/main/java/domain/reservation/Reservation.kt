package domain.reservation

import domain.discount.MovieDiscountEvent
import domain.movie.Movie
import domain.movie.MovieName
import domain.payment.PaymentAmount
import domain.payment.PaymentType
import java.time.LocalDateTime

data class Reservation(
    val movieName: MovieName,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) {

    companion object {
        private const val TICKET_PRICE = 13000

        fun from(movie: Movie, ticketCount: Int, screeningDateTime: LocalDateTime): Reservation {
            val paymentAmount: PaymentAmount = MovieDiscountEvent().discount(
                PaymentAmount(ticketCount * TICKET_PRICE),
                screeningDateTime
            )

            return Reservation(
                movieName = movie.movieName,
                screeningDateTime = screeningDateTime,
                ticketCount = ticketCount,
                paymentAmount = paymentAmount,
            )
        }
    }
}
