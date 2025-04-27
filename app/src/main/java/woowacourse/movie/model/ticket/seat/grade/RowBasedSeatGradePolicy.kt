package woowacourse.movie.model.ticket.seat.grade

import woowacourse.movie.model.ticket.seat.Seat

class RowBasedSeatGradePolicy : SeatGradePolicy {
    override fun getGrade(seat: Seat): SeatGrade =
        when (seat.row.value) {
            0, 1 -> SeatGrade.B
            2, 3 -> SeatGrade.S
            4 -> SeatGrade.A
            else -> throw IllegalArgumentException("유효하지 않은 좌석 행입니다: ${seat.row.value}")
        }
}
