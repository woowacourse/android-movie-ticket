package woowacourse.movie.view.data

import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats

data class SeatTable(val seats: Seats, val size: TableSize) {
    fun getSeat(row: Int, column: Int): Seat {
        return seats.value.find {
            it.row.row == row && it.column == column
        } ?: throw IllegalArgumentException(ERROR_WRONG_SEAT.format(row, column))
    }

    companion object {
        private const val ERROR_WRONG_SEAT = "입력한 행과 열에 좌석이 없습니다. 행 : %d, 열 : %d"
    }
}
