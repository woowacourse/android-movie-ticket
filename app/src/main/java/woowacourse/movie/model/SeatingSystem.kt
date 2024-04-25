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
}
