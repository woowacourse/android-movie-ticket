package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieSeat

interface SeatRepository {
    fun getAvailableSeats(): List<List<MovieSeat>>

    fun getAvailableSeat(
        row: Int,
        column: Int,
    ): MovieSeat

    fun getSeatRowAndColumn(seats: List<MovieSeat>): List<Pair<Int, Int>>
}
