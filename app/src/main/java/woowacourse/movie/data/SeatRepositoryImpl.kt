package woowacourse.movie.data

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.repository.SeatRepository

class SeatRepositoryImpl : SeatRepository {
    override fun getSeats(): List<List<MovieSeat>> {
        return MockSeats.sampleSeats
    }

    override fun getSeat(
        row: Int,
        column: Int,
    ): MovieSeat {
        return try {
            MockSeats.sampleSeats[row][column]
        } catch (e: IndexOutOfBoundsException) {
            MockSeats.defaultSeat
        }
    }

    override fun getSeatRowAndColumn(seats: List<MovieSeat>): List<Pair<Int, Int>> {
        val seatPositions = mutableListOf<Pair<Int, Int>>()
        for (seat in seats) {
            for ((rowIndex, row) in MockSeats.sampleSeats.withIndex()) {
                for ((columnIndex, currentSeat) in row.withIndex()) {
                    if (currentSeat == seat) {
                        seatPositions.add(Pair(rowIndex, columnIndex))
                    }
                }
            }
        }
        return seatPositions
    }
}
