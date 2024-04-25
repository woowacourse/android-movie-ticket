package woowacourse.movie.data

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.repository.SeatRepository

class SeatRepositoryImpl : SeatRepository {
    override fun getSeats(): List<List<MovieSeat>> {
        return MockSeats.sampleSeats
    }

    override fun getSeat(row: Int, column: Int): MovieSeat {
        return try {
            MockSeats.sampleSeats[row][column]
        } catch (e: IndexOutOfBoundsException){
            MockSeats.defaultSeat
        }
    }
}
