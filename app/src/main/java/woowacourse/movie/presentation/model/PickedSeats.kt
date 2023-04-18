package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PickedSeats(val seats: List<Seat> = emptyList()) : Parcelable {
    fun sorted(): PickedSeats = PickedSeats(seats.sorted())

    override fun toString(): String =
        seats.joinToString(", ") { it.toString() }
}
