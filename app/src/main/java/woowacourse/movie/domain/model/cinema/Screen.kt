package woowacourse.movie.domain.model.cinema

import woowacourse.movie.domain.model.cinema.seat.Seat
import woowacourse.movie.domain.model.cinema.seat.SeatType

class Screen(
    size: ScreenSize,
) {
    val seats: List<Seat> =
        (0 until size.rowSize).flatMap { rowIndex ->
            val seatType = calculateSeatType(rowIndex)
            (1..size.colSize).map { colIndex ->
                Seat(rowIndex, colIndex, seatType)
            }
        }

    private fun calculateSeatType(row: Int): SeatType =
        when (row) {
            in 0..1 -> SeatType.B_CLASS
            in 2..3 -> SeatType.S_CLASS
            else -> SeatType.A_CLASS
        }

    companion object {
        val DEFAULT_SCREEN = Screen(ScreenSize(5, 4))
    }
}
