package woowacourse.movie.model

import domain.payment.PaymentType
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationInfo(
    val movieName: String,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: Int,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {

        fun ofError() = ReservationInfo(
            "",
            LocalDateTime.MIN,
            0,
            0,
            PaymentType.ERROR_PAID
        )
    }
}

fun domain.reservation.Reservation.toUIModel() = ReservationInfo(
    movieName.value,
    screeningDateTime,
    ticketCount,
    paymentAmount.value,
    paymentType
)
