package domain

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

        fun from(movie: Movie, ticketCount: Int, screeningDateTime: LocalDateTime) = Reservation(
            movie = movie,
            screeningDateTime = screeningDateTime,
            ticketCount = ticketCount,
            paymentAmount = PaymentAmount(ticketCount * TICKET_PRICE),
        )
    }
}
