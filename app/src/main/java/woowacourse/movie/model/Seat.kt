package woowacourse.movie.model

import woowacourse.movie.model.SeatClass.Companion.determineSeatGrade

data class Seat(val row: Int, val col: Int) {
    val seatClass: SeatClass = determineSeatGrade(row + 1)
}
