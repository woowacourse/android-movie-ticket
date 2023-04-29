package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.seat.SeatRow
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatPositionState(
    val row: Int,
    val column: Int
) : Parcelable {
    override fun toString(): String {
        val rowEnum = SeatRow.valueOf(row)
        return "$rowEnum$column"
    }
}
