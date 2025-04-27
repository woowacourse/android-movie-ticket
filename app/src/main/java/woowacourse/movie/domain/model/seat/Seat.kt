package woowacourse.movie.domain.model.seat

import java.io.Serializable

data class Seat(val seatPosition: SeatPosition) : Serializable {
    val grade: SeatGrade = SeatGrade.from(seatPosition.y)
}
