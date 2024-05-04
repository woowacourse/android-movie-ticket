package woowacourse.movie.domain.model.reservation.seat

class SelectedSeats(
    private val seatingChart: SeatingChart,
    private val reservationCount: Int,
) {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat>
        get() = _seats

    fun tryAddOrDeleteSeat(
        row: Int,
        col: Int,
    ): Boolean {
        return when (isAlreadySelected(row, col)) {
            true -> removeSelectedSeat(row, col)
            false -> tryAddSelectedSeat(row, col)
        }
    }

    private fun isAlreadySelected(
        row: Int,
        col: Int,
    ): Boolean {
        return seats.any { it.row == row && it.col == col }
    }

    private fun tryAddSelectedSeat(
        row: Int,
        col: Int,
    ): Boolean {
        if (!isMatchedTheCount()) {
            val seat = seatingChart.classifySeatByRow(row, col)
            _seats.add(seat)
            return true
        }
        return false
    }

    private fun removeSelectedSeat(
        row: Int,
        col: Int,
    ): Boolean {
        _seats.removeIf { it.row == row && it.col == col }
        return true
    }

    fun isMatchedTheCount(): Boolean {
        return seats.size == reservationCount
    }

    fun totalPrice(): Int {
        return seats.map { it.rank }.sumOf { it.price }
    }
}
