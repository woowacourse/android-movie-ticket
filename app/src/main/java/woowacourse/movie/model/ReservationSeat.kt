package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationSeat(
    val reservationState: ReservationState,
    val seats: List<SeatPositionState>
) : Parcelable {
    init {
        require(reservationState.countState.value == seats.size) {}
    }
}
