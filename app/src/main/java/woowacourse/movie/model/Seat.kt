package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.SeatRank
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(val rank: SeatRank, val row: Int) : Parcelable {
    fun convertString(): String {
        return rank.toString() + row.toString()
    }
}
