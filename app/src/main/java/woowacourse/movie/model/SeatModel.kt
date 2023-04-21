package woowacourse.movie.model

class SeatModel(row: Int, col: Int) : java.io.Serializable {
    val seatId: String
    init {
        seatId = ('A'.code + row).toString() + col.toString()
    }
}
