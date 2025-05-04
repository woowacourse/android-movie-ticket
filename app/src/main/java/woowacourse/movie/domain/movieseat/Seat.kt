package woowacourse.movie.domain.movieseat

import SeatRank
import java.io.Serializable

data class Seat(
    val position: Position,
    val rank: String,
) : Serializable {
    fun seatPrice() = SeatRank.from(rank).price
}
