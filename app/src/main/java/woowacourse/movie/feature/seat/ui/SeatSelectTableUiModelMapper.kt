package woowacourse.movie.feature.seat.ui

import woowacourse.movie.R
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatRating
import woowacourse.movie.model.Seats

fun Seats.toSeatSelectTableUiModels(): List<List<SeatSelectTableUiModel>> {
    return table.map { row ->
        row.map { seat ->
            SeatSelectTableUiModel(seat.message(), SeatRating.from(seat).colorId())
        }
    }
}

private fun Seat.message(): String {
    return "${(row + 'A'.code - 1).toChar()}$col"
}

private fun SeatRating.colorId(): Int {
    return when (this) {
        SeatRating.B -> R.color.purple
        SeatRating.S -> R.color.green
        SeatRating.A -> R.color.blue
    }
}
