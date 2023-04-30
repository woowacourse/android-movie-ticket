package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import movie.domain.seat.RowSeat

@Parcelize
data class SeatState(val index: Int) : Parcelable {
    override fun toString(): String {
        return RowSeat.valueOf(index / COLUMN_SEAT_COUNT).toString() + (index % COLUMN_SEAT_COUNT + 1).toString()
    }

    companion object {
        private const val COLUMN_SEAT_COUNT = 4
    }
}
