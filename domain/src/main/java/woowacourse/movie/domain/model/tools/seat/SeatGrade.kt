package woowacourse.movie.domain.model.tools.seat

import woowacourse.movie.domain.model.tools.Money

enum class SeatGrade(val ticketPrice: Money) {
    GRADE_B(Money(10000)), GRADE_S(Money(15000)), GRADE_A(Money(12000))
    ;

    companion object {
        fun from(location: Location) = when (location.row) {
            SeatRow.A, SeatRow.B -> GRADE_B
            SeatRow.D, SeatRow.C -> GRADE_S
            SeatRow.E -> GRADE_A
        }
    }
}
