package woowacourse.movie.view.seatSelection

import woowacourse.movie.model.seat.Seat

object SeatSelectionFormatter {
    @JvmStatic
    fun seatsToUI(
        seats: List<Seat>,
        separator: String,
    ): String =
        seats
            .sortedBy { it.column.value }
            .sortedBy { it.row.value }
            .joinToString(separator) { seat -> seatToUI(seat) }

    @JvmStatic
    fun seatToUI(seat: Seat): String = "${rowToUI(seat.row.value)}${columnToUI(seat.column.value)}"

    @JvmStatic
    fun rowToUI(row: Int): String = (Char('A'.code) + row).toString()

    @JvmStatic
    fun columnToUI(column: Int): String = (column + 1).toString()
}
