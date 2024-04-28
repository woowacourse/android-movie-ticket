package woowacourse.movie.db.seats

import woowacourse.movie.model.seats.Seat

class SeatsDao {
    private val seats: List<Seat> = SeatsDatabase.seats

    fun findAll(): List<Seat> = seats
}
