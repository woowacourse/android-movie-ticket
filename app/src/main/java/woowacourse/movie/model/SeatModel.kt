package woowacourse.movie.model

class SeatModel(row: Int, col: Int) {
    val seatId: String

    init {
        seatId = ('A'.code + row).toString() + col.toString()
    }
}
