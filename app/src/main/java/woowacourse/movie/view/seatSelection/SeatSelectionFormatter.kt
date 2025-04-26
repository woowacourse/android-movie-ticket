package woowacourse.movie.view.seatSelection

import woowacourse.movie.model.seat.Seat

object SeatSelectionFormatter {
    @JvmStatic
    fun seatToUI(seat: Seat): String = rowToUI(seat.row.value) + seat.column.value.toString()

    @JvmStatic
    fun rowToUI(row: Int): String = (Char('A'.code) + row).toString()

    @JvmStatic
    fun columnToUI(column: Int): String = (column + 1).toString()
}
