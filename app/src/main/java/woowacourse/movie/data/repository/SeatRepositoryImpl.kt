package woowacourse.movie.data.repository

import woowacourse.movie.domain.model.reservation.seat.SeatingChart
import woowacourse.movie.domain.repository.SeatRepository

object SeatRepositoryImpl : SeatRepository {
    private const val ROW_SEAT_COUNT = 5
    private const val COL_SEAT_COUNT = 4

    override fun getSeatingChart(): SeatingChart {
        return SeatingChart(ROW_SEAT_COUNT, COL_SEAT_COUNT)
    }
}
