package woowacourse.movie.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(val row: Int, val col: Int) : Parcelable {
    fun amount() = SeatRating.from(this).amount
}
