package woowacourse.movie.model

class MovieSeats(private val rowSize: Int = 5, private val columnSize: Int = 4) {
    val seats =
        List(rowSize * columnSize) { index ->
            val row = index / columnSize
            val column = index % columnSize
            MovieSeat(row, column)
        }
}
