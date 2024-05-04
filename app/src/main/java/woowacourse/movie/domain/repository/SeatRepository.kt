package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.reservation.seat.SeatingChart

interface SeatRepository {
    fun getSeatingChart(): SeatingChart
}
