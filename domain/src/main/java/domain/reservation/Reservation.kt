package domain.reservation

import domain.discount.MovieDiscountEvent
import domain.movie.MovieName
import domain.payment.PaymentAmount
import domain.payment.PaymentType
import domain.seat.ScreeningSeat
import java.time.LocalDateTime

data class Reservation(
    val movieName: MovieName,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val seats: List<ScreeningSeat>,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) {

    companion object {

        fun of(
            movieName: String,
            ticketCount: Int,
            screeningDateTime: LocalDateTime,
            paymentAmount: PaymentAmount,
            seats: List<ScreeningSeat>
        ): Reservation {
            val discountedPaymentAmount: PaymentAmount = MovieDiscountEvent().discount(
                paymentAmount,
                screeningDateTime
            )

            return Reservation(
                movieName = MovieName(movieName),
                screeningDateTime = screeningDateTime,
                ticketCount = ticketCount,
                paymentAmount = discountedPaymentAmount,
                seats = seats,
            )
        }
    }
}
