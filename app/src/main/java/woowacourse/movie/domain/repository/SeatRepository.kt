package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieSeat

interface SeatRepository {
    fun getSeats(): List<List<MovieSeat>>

    fun getSeat(row: Int, column: Int): MovieSeat

    fun getSeatRowAndColumn(seats: List<MovieSeat>): List<Pair<Int, Int>>
}
