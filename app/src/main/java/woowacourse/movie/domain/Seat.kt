package woowacourse.movie.domain

import java.io.Serializable

data class Seat(
    val position: Position,
) : Serializable {
    fun seatPrice() = SeatRank.get(position.row).price
}
