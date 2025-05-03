package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatsUiModel(
    val availableSelectCount: String,
    val reservedSeats: Set<SeatUiModel>,
    val reservingSeats: Set<SeatUiModel>,
) : Parcelable {
    fun reservingSeatsNames(): String {
        return reservingSeats.joinToString { "${it.row.row}${it.col.col}" }
    }
}
