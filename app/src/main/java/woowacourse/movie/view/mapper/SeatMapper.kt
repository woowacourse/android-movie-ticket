package woowacourse.movie.view.mapper

import com.example.domain.Seat
import com.example.domain.SeatType
import woowacourse.movie.R

fun Seat.toUi(): String {
    return ('A' + row - 1).toString() + "$column"
}

fun Seat.textColor(): Int {
    return when (this.type) {
        SeatType.BType -> R.color.purple_700
        SeatType.SType -> R.color.green
        SeatType.AType -> R.color.blue
    }
}
