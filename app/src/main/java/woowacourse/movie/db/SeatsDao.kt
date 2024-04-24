package woowacourse.movie.db

import woowacourse.movie.model.Seat

class SeatsDao {
    private val seats: List<Seat> = SeatsDatabase.seats

    fun findAll(): List<Seat> = seats
}
