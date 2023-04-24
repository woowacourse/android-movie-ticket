package woowacourse.movie.domain.model.tools.seat

import woowacourse.movie.domain.model.tools.Money

enum class SeatGrade(val ticketPrice: Money) {
    GRADE_B(Money(10000)), GRADE_S(Money(15000)), GRADE_A(Money(12000))
}
