package woowacourse.movie.model

import woowacourse.movie.model.SeatClass.Companion.determineSeatGrade
import java.io.Serializable

data class Seat(val row: Int, val col: Int) : Serializable {
    val seatClass: SeatClass = determineSeatGrade(row + 1)
}
