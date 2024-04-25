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

    private val _selectedSeats: MutableList<Seat> = mutableListOf()
    val selectedSeats: List<Seat>
        get() = _selectedSeats.toList()

    fun selectSeat(
        row: Int,
        col: Int,
    ) {
        val selected = seats[row * colSize + col]
        _selectedSeats.add(selected)
    }

    fun deselectSeat(
        row: Int,
        col: Int,
    ) {
        val selected = seats[row * colSize + col]
        _selectedSeats.remove(selected)
    }
}
