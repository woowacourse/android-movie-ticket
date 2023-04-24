package woowacourse.movie.domain.theater

import woowacourse.movie.domain.price.Price
import woowacourse.movie.domain.seat.Grade

class TheaterInfo(
    private val rowGrade: Map<Int, Grade>,
    private val seatRow: Int,
    private val seatCol: Int
) {
    fun getGradePrice(row: Int): Price? = rowGrade[row]?.price

    fun isValidSeat(selectRow: Int, selectCol: Int): Boolean = selectRow < seatRow && selectCol < seatCol
}
