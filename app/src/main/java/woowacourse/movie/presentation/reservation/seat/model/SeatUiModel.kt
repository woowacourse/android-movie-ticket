package woowacourse.movie.presentation.reservation.seat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatUiModel(
    val x: Int,
    val y: Int,
    val state: SeatStateUiModel,
    val seatGradeUiModel: SeatGradeUiModel,
    val price: Long,
) : Parcelable {
    fun formatSeatPosition(): String {
        val row = 'A' + x
        val col = y + 1
        return "$row$col"
    }
}
