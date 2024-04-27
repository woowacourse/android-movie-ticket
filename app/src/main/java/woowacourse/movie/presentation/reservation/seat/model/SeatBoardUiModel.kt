package woowacourse.movie.presentation.reservation.seat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatBoardUiModel(
    val columnCount: Int,
    val rowCount: Int,
    val headCount: Int,
    val seats: List<SeatUiModel>,
) : Parcelable
