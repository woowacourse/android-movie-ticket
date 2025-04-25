package woowacourse.movie.domain

import java.io.Serializable

data class Seat(
    val position: Position,
) : Serializable {
    val grade: SeatGrade = SeatGrade.of(position.row)
}
