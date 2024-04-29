package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.SeatGrade.Companion.determineSeatGrade

@Parcelize
data class Seat(val row: Int, val col: Int) : Parcelable {
    val seatGrade: SeatGrade = determineSeatGrade(row + 1)
}
