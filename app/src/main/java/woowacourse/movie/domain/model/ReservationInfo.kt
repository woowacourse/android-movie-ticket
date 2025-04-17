package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ReservationInfo(
    val title: String,
    val reservationDateTime: String,
    val reservationNumber: Int,
) : Parcelable {
    fun totalPrice(): Int = reservationNumber * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13_000
        const val RESERVATION_MIN_NUMBER = 1
    }
}
