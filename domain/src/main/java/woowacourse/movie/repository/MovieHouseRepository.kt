package woowacourse.movie.repository

import woowacourse.movie.domain.MovieHouse
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.SeatClass

object MovieHouseRepository {

    private val movieHouses: MutableMap<Long, MovieHouse> = mutableMapOf()
    private const val INIT_ID = 1L
    var nextId: Long = INIT_ID
        private set

    init {
        addSampleData()
    }

    private fun addSampleData() {
        val movieHouseId = nextId++
        val seats = createSampleSeats()
        movieHouses[movieHouseId] = MovieHouse(movieHouseId, seats)
    }

    private fun createSampleSeats(): Set<Seat> =
        (1..5).flatMap { row -> (1..4).map { column -> Seat(row, column, getSeatClass(row)) } }
            .toSet()


    private fun getSeatClass(row: Int): SeatClass =
        when (row) {
            1, 2 -> SeatClass.B
            3, 4 -> SeatClass.S
            else -> SeatClass.A
        }

    fun findById(id: Long): MovieHouse? {
        return movieHouses[id]
    }
}
