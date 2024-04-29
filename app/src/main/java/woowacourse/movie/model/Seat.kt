package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.SeatClass.Companion.determineSeatGrade

@Parcelize
data class Seat(val row: Int, val col: Int) : Parcelable {
    val seatClass: SeatClass = determineSeatGrade(row + 1)
}
