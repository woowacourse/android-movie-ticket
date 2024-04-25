package woowacourse.movie.model

class SeatingSystem(
    private val rowSize: Int = 5,
    private val colSize: Int = 4,
) {
    val seats =
        List(rowSize * colSize) { index ->
            val row = index / colSize
            val col = index % colSize
            Seat(row, col)
        }

    private val _selectedSeats: MutableSet<Seat> = mutableSetOf()
    val selectedSeats: Set<Seat>
        get() = _selectedSeats.toSet()

    fun isSelected(index: Int): Boolean = seats[index] in selectedSeats

    fun selectSeat(index: Int) {
        val selected = seats[index]
        _selectedSeats.add(selected)
    }

    fun unSelectSeat(index: Int) {
        val selected = seats[index]
        _selectedSeats.remove(selected)
    }
}
