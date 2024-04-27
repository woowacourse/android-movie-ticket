package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Seats

interface SeatRepository {
    fun getAllSeats(): Seats
}
