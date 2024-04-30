package woowacourse.movie.model

class MovieSelectedSeats(
    val count: Int,
    rowSize: Int = DEFAULT_ROW_SIZE,
    private val columnSize: Int = DEFAULT_COLUMN_SIZE,
) {
    private val baseSeats =
        List(rowSize * columnSize) { index ->
            val row = index / columnSize
            val column = index % columnSize
            MovieSeat(row, column)
        }

    private val _selectedSeats: MutableSet<MovieSeat> = mutableSetOf()
    val selectedSeats: Set<MovieSeat>
        get() = _selectedSeats.sortedWith(compareBy({ it.row }, { it.column })).toSet()

    fun selectSeat(seat: MovieSeat) {
        _selectedSeats.add(seat)
    }

    fun unSelectSeat(seat: MovieSeat) {
        _selectedSeats.remove(seat)
    }

    fun isSelected(index: Int): Boolean = baseSeats[index] in selectedSeats

    fun totalPrice(): Int = selectedSeats.sumOf { selectedSeat -> selectedSeat.grade.price }

    fun isSelectionComplete(): Boolean = count == selectedSeats.size

    fun getSelectedPositions(): IntArray {
        return selectedSeats.map { seat ->
            seat.row * columnSize + seat.column
        }.toIntArray()
    }

    fun getBaseSeats(): List<MovieSeat> = baseSeats

    companion object {
        private const val DEFAULT_ROW_SIZE = 5
        private const val DEFAULT_COLUMN_SIZE = 4
    }
}
