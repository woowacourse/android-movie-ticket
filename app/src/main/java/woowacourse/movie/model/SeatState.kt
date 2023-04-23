package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import movie.domain.seat.RowSeat

@Parcelize
data class SeatState(val index: Int) : Parcelable {
    override fun toString(): String {
        return RowSeat.valueOf(index / 4).toString() + (index % 4 + 1).toString()
    }
}
