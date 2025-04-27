package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(
    val row: Int,
    val column: Int,
) : Parcelable {
    fun price() = SeatGrade.of(this).price
}
