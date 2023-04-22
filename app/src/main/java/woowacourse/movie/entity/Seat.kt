package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(val rank: SeatRank, val row: Int) : Parcelable {
    fun convertString(): String {
        return rank.toString() + row.toString()
    }
}
