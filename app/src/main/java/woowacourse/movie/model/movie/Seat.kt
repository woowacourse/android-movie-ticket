package woowacourse.movie.model.movie

data class Seat(val row: SeatRow, val col: Int) {
    constructor(row: Int, col: Int) : this(SeatRow.findRow(row), col)

    fun price() =
        when (row) {
            SeatRow.A, SeatRow.B -> SeatClass.B.amount
            SeatRow.C, SeatRow.D -> SeatClass.S.amount
            SeatRow.E -> SeatClass.A.amount
        }

    override fun toString() = row.toString() + (col + OFFSET)

    companion object {
        const val ROW_LEN = 5
        const val COL_LEN = 4
        private const val OFFSET = 1
    }
}
