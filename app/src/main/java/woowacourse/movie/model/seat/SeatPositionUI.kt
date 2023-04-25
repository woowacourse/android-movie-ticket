package woowacourse.movie.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatPositionUI(
    val row: RowUI,
    val col: ColUI
) : Parcelable {
    fun getSeatPositionDisplay(): String = "$row$col"
}
