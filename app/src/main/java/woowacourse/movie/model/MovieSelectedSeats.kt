package woowacourse.movie.model

class MovieSelectedSeats(
    val count: Int,
    rowSize: Int = 5,
    private val columnSize: Int = 4,
) {
    val baseSeats =
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
}
