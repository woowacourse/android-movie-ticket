package woowacourse.movie.model.seat

import java.io.Serializable

class Seat(val row: Int, val col: Int) : Serializable {
    fun amount() = SeatRating.from(this).amount
}
