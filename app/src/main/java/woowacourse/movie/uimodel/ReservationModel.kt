package woowacourse.movie.uimodel

import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.payment.PaymentType
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationModel(
    val movie: MovieModel,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val seats: List<SeatModel>,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {
        const val RESERVATION_INTENT_KEY = "woowacourse/movie/domain/reservation"
        const val SCREENING_DATE_INSTANCE_KEY = "screening_date"
        const val SCREENING_TIME_INSTANCE_KEY = "screening_time"
        const val SCREENING_DATE_TIME_INTENT_KEY = "screening_date_time"
    }
}
