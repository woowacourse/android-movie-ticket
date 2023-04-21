package reservation

import movie.Movie
import payment.PaymentAmount
import payment.PaymentType
import seat.Seat
import java.time.LocalDateTime

data class Reservation(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val seats: List<Seat>,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
)
