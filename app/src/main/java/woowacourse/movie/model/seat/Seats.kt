package woowacourse.movie.model.seat

class Seats(
    row: Int,
    col: Int,
) {
    val table: List<List<Seat>> by lazy {
        List(row) { r -> List(col) { c -> Seat(r + 1, c + 1) } }
    }
}
