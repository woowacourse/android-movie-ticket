package woowacourse.movie.model.ticket.seat.grade

import woowacourse.movie.model.ticket.seat.Seat

interface SeatGradePolicy {
    fun getGrade(seat: Seat): SeatGrade
}
