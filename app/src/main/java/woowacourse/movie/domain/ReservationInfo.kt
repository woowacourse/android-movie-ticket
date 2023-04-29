package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.ui.seatreservation.uimodel.Seat

@Parcelize
data class ReservationInfo(
    val ticket: Ticket,
    val total: String,
    val seat: List<Seat>,
) : Parcelable
