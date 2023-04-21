package woowacourse.movie.view.mapper

import com.example.domain.Seat
import com.example.domain.SeatType
import woowacourse.movie.R
import woowacourse.movie.view.model.SeatUiModel

fun Seat.toUiModel(): SeatUiModel = SeatUiModel(
    ('A' + row - 1).toString() + "$column",
    when (this.type) {
        SeatType.BType -> R.color.purple_700
        SeatType.SType -> R.color.green
        SeatType.AType -> R.color.blue
    }
)
