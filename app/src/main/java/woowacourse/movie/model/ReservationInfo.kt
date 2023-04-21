package woowacourse.movie.model

import domain.payment.PaymentType
import domain.reservation.Reservation
import domain.seat.SeatColumn
import domain.seat.SeatRow
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationInfo(
    val movieName: String,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: Int,
    val seats: List<Pair<SeatRow, SeatColumn>>,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {
        fun ofError() = ReservationInfo(
            "",
            LocalDateTime.MIN,
            0,
            0,
            listOf(),
            PaymentType.ERROR_PAID
        )
    }
}

fun Reservation.toUIModel() = ReservationInfo(
    movieName.value,
    screeningDateTime,
    ticketCount.value,
    paymentAmount.value,
    seats.map { it.row to it.column },
    paymentType
)
