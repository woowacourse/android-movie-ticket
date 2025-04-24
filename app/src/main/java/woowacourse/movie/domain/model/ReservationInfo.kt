package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class ReservationInfo(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: ReservationCount,
) : Parcelable {
    fun totalPrice(): Int = reservationCount.value * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}
