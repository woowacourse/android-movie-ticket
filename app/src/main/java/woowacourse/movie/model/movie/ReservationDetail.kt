package woowacourse.movie.model.movie

class ReservationDetail(
    val reservationCount: Int,
    private val _selectedSeat: MutableList<Seat> = mutableListOf(),
) {
    val selectedSeat: List<Seat>
        get() = _selectedSeat.toList().sortedWith(compareBy({ it.row }, { it.col }))

    fun addSeat(
        row: Int,
        col: Int,
    ): Boolean {
        if (isSelectable()) {
            _selectedSeat.add(Seat(row, col))
            return true
        }
        return false
    }

    private fun isSelectable() = _selectedSeat.size < reservationCount

    fun removeSeat(
        row: Int,
        col: Int,
    ) {
        _selectedSeat.remove(Seat(row, col))
    }

    fun totalSeatAmount(): Int = _selectedSeat.sumOf { it.price() }

    fun checkSelectCompletion() = _selectedSeat.size == reservationCount
}
