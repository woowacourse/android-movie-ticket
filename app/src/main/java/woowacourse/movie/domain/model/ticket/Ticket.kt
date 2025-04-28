package woowacourse.movie.domain.model.ticket

import woowacourse.movie.domain.model.reservation.Price
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatRate
import java.io.Serializable

class Ticket(val seat: Seat) : Serializable {
    val price = seatRatePrice(seat.seatRate).value

    val position: Pair<Row, Column> = Pair(seat.row, seat.column)

    private fun seatRatePrice(seatRate: SeatRate): Price {
        return when (seatRate) {
            SeatRate.S -> Price(S_CLASS_PRICE)
            SeatRate.A -> Price(A_CLASS_PRICE)
            SeatRate.B -> Price(B_CLASS_PRICE)
        }
    }

    companion object {
        private const val S_CLASS_PRICE = 1_5000
        private const val A_CLASS_PRICE = 1_2000
        private const val B_CLASS_PRICE = 1_0000
    }
}
