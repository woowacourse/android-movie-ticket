package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(
    val reservationInfo: ReservationInfo,
    val seat: Set<String>,
    val totalPrice: Int,
): Parcelable