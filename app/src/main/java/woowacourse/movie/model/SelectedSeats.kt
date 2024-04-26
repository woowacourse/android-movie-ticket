package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.io.Serializable

class SelectedSeats(val reservationCount: ReservationCount): Serializable {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat>
        get() = _seats.toList()

    fun add(seat: Seat) {
        _seats.add(seat)
    }

    fun remove(seat: Seat) {
        _seats.remove(seat)
    }

    fun isSelectable(): Boolean = _seats.size < reservationCount.count

    fun isConfirm(): Boolean = _seats.size == reservationCount.count

    operator fun contains(seat: Seat) = _seats.contains(seat)
}
