package woowacourse.movie.domain

import java.io.Serializable

data class Seat(
    val position: Position,
) : Serializable {
    private val grade: SeatGrade = SeatGrade.of(position.row)
    val price: Int = grade.price
}
