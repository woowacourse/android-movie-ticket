package woowacourse.movie.model.movie

enum class SeatRow(val row: Int) {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    ;

    companion object {
        fun findRow(row: Int): SeatRow {
            return entries.first { it.row == row }
        }
    }
}
