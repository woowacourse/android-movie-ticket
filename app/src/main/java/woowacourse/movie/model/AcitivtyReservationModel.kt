package woowacourse.movie.model

import domain.payment.PaymentAmount
import domain.payment.PaymentType
import domain.reservation.Reservation
import java.io.Serializable
import java.time.LocalDateTime

data class ActivityReservationModel(
    val movieName: String,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable

fun Reservation.toActivityModel() = ActivityReservationModel(
    movieName.value,
    screeningDateTime,
    ticketCount,
    paymentAmount,
    paymentType
)
