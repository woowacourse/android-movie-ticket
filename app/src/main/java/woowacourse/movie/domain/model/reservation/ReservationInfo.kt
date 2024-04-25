package woowacourse.movie.domain.model.reservation

import woowacourse.movie.domain.model.reservation.seat.SeatingChart
import woowacourse.movie.domain.model.reservation.seat.SelectedSeats

class ReservationInfo(
    val reservationCount: Int,
    seatingChart: SeatingChart,
) {
    val selectedSeats = SelectedSeats(seatingChart, reservationCount)
}
