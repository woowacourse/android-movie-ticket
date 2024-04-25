package woowacourse.movie.model

class MovieSeats(private val rowSize: Int = 5, private val columnSize: Int = 4) {
    val seats =
        List(rowSize * columnSize) { index ->
            val row = index / rowSize
            val column = index % rowSize
            MovieSeat(row, column)
        }
}
