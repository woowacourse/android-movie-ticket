package woowacourse.movie.uimodel

import woowacourse.movie.domain.seat.Column
import woowacourse.movie.domain.seat.Row
import woowacourse.movie.domain.seat.SeatType
import java.io.Serializable

data class SeatModel(
    val row: Row,
    val column: Column,
    val seatType: SeatType?
) : Serializable {

    override fun toString(): String {
        return "$row$column"
    }
}
