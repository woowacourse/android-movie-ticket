package woowacourse.movie.domain.reservation

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.payment.PaymentType
import woowacourse.movie.domain.seat.Seat
import java.time.LocalDateTime

class TicketOffice {
    companion object {
        fun generateTicket(
            movie: Movie,
            screeningDateTime: LocalDateTime,
            seats: List<Seat>
        ): Reservation {
            val paymentAmount = PaymentAmount.applyDiscount(seats, screeningDateTime)

            return Reservation(
                movie = movie,
                screeningDateTime = screeningDateTime,
                ticketCount = seats.size,
                seats = seats,
                paymentAmount = paymentAmount,
                paymentType = PaymentType.LOCAL_PAYMENT
            )
        }
    }
}
