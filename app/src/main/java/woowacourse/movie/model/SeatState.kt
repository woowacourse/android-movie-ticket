package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import movie.domain.seat.RowSeat

@Parcelize
data class SeatState(val row: Int, val col: Int) : Parcelable {
    override fun toString(): String {
        return RowSeat.valueOf(row).name + col.toString()
    }
}
