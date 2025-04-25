package woowacourse.movie.domain.model.cinema

import woowacourse.movie.domain.model.cinema.seat.Seat
import woowacourse.movie.domain.model.cinema.seat.SeatType

class Screen private constructor(
    val seats: List<Seat>,
) {
    companion object {
        fun create(size: ScreenSize): Screen {
            val seats =
                (0 until size.rowSize).flatMap { rowIndex ->
                    val seatType = calculateSeatType(rowIndex)
                    (1..size.colSize).map { colIndex ->
                        Seat(rowIndex, colIndex, seatType)
                    }
                }
            return Screen(seats)
        }

        private fun calculateSeatType(row: Int): SeatType =
            when (row) {
                in 0..1 -> SeatType.B_CLASS
                in 2..3 -> SeatType.S_CLASS
                else -> SeatType.A_CLASS
            }
    }
}
