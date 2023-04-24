package woowacourse.movie.domain.model.rules

import woowacourse.movie.domain.model.tools.seat.Location
import woowacourse.movie.domain.model.tools.seat.SeatGrade
import woowacourse.movie.domain.model.tools.seat.SeatRow

object SeatGradeRules {

    fun getSeatGradeByRow(location: Location) = when (location.row) {
        SeatRow.A, SeatRow.B -> SeatGrade.GRADE_B
        SeatRow.D, SeatRow.C -> SeatGrade.GRADE_S
        SeatRow.E -> SeatGrade.GRADE_A
    }
}
