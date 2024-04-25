package woowacourse.movie.model.movie

class ReservationDetail(private val reservationCount: Int) {
    private val _selectedSeat = mutableListOf<Seat>()
    val selectedSeat: List<Seat>
        get() = _selectedSeat.toList()

    fun addSeat(
        row: Int,
        col: Int,
    ) {
        _selectedSeat.add(Seat(row, col))
    }

    fun removeSeat(
        row: Int,
        col: Int,
    ) {
        _selectedSeat.remove(Seat(row, col))
    }

    fun totalSeatAmount(): Int = _selectedSeat.sumOf { it.price() }
}
