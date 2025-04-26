package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.cinema.seat.Seat

@Parcelize
data class SeatUiModel(
    val row: Int,
    val col: Int,
    val type: SeatTypeUiModel,
) : Parcelable {
    override fun toString(): String = "${'A' + row}$col"
}

fun Seat.toUiModel(): SeatUiModel = SeatUiModel(row, col, type.toUiModel())

fun SeatUiModel.toModel(): Seat = Seat(row, col, type.toModel())
