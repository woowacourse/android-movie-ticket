package woowacourse.movie.model

class Seats(
    private val row: Int,
    private val col: Int,
) {
    val table: List<List<Seat>> by lazy {
        List(row) { r -> List(col) { c -> Seat(r + 1, c + 1) } }
    }
}
