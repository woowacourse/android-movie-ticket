package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TicketsUI(
    val tickets: Set<TicketUI>,
    val reservation: ReservationUI
) : Parcelable {
    fun getSeatPositionUIFormat() =
        tickets.joinToString(", ") { it.seatPosition.getSeatPositionUIFormat() }
}
