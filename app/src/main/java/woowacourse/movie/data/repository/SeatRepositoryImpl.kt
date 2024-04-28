package woowacourse.movie.data.repository

import woowacourse.movie.data.SampleSeat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.SeatRepository

object SeatRepositoryImpl : SeatRepository {
    private val seats: Seats = SampleSeat.seats

    override fun getAllSeats(): Seats {
        return seats
    }
}
