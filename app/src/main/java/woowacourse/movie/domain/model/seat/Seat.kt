package woowacourse.movie.domain.model.seat

import java.io.Serializable

data class Seat(val position: Position) : Serializable {
    val grade: SeatGrade = SeatGrade.from(position.y)
}
