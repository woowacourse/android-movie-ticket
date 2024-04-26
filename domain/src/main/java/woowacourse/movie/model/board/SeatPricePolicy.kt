package woowacourse.movie.model.board

import woowacourse.movie.model.Price

interface SeatPricePolicy {
    fun price(grade: SeatGrade): Price
}

object DefaultSeatPricePolicy : SeatPricePolicy {
    override fun price(grade: SeatGrade): Price {
        return when (grade) {
            SeatGrade.S -> Price(15_000L)
            SeatGrade.A -> Price(12_000L)
            SeatGrade.B -> Price(10_000L)
        }
    }
}
