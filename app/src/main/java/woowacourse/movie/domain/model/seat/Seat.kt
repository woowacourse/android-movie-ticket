package woowacourse.movie.domain.model.seat

import java.io.Serializable

data class Seat(
    val x: Column,
    val y: Row,
) : Serializable {
    fun seatPrice() = SeatPolicy.get(x.value).price
}
