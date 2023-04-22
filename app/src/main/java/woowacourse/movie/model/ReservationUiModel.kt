package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.PaymentType
import java.time.LocalDateTime

@Parcelize
data class ReservationUiModel(
    val tickets: Set<TicketUiModel>,
    val paymentType: PaymentType = PaymentType.OFFLINE,
    val payment: Int,
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) : Parcelable {
    val seatPosition: String
        get() = tickets.joinToString(", ") { it.coordinate }
}
