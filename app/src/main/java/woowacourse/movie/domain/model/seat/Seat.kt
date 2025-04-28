package woowacourse.movie.domain.model.seat

import java.io.Serializable

data class Seat(
    val x: Int,
    val y: Int,
) : Serializable {
    fun seatPrice() = SeatPolicy.get(x).price
}
