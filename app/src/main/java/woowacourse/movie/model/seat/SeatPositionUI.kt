package woowacourse.movie.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatPositionUI(
    val row: RowUI,
    val col: ColUI
) : Parcelable {
    fun getSeatPositionUIFormat() =
        POSITION_UI_FORMAT.format(('A' + row.x), (col.y + COLUMN_UI_CONDITION))

    companion object {
        private const val POSITION_UI_FORMAT = "%c%d"
        private const val COLUMN_UI_CONDITION = 1
    }
}
