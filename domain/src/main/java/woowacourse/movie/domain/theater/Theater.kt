package woowacourse.movie.domain.theater

import woowacourse.movie.domain.discount.Money
import kotlin.properties.Delegates

class Theater(seatRows: Int, seatColumns: Int) {

    var id: Long? by Delegates.vetoable(null) { _, old, new ->
        old == null && new != null
    }

    val seats: Map<Point, SeatClass> = createSeats(seatRows, seatColumns)

    init {
        require(seatRows.isPositive() && seatColumns.isPositive()) { SEAT_ROWS_OR_COLUMNS_NOT_POSITIVE_ERROR }
    }

    private fun Int.isPositive(): Boolean = this > 0

    private fun createSeats(rows: Int, columns: Int): Map<Point, SeatClass> {
        return (1..rows)
            .flatMap { row -> (1..columns).map { col -> Point(row, col) } }
            .associateWith { getSeatClassOf(it) }
    }

    private fun getSeatClassOf(point: Point): SeatClass =
        when (point.row) {
            1, 2 -> SeatClass.B
            3, 4 -> SeatClass.S
            else -> SeatClass.A
        }

    fun hasSeatOn(point: Point): Boolean = point in seats.keys

    fun getFeeOf(seatPoint: Point): Money? = seats[seatPoint]?.seatFee

    companion object {
        private const val SEAT_ROWS_OR_COLUMNS_NOT_POSITIVE_ERROR = "좌석들의 행과 열의 개수는 양수여야 합니다."
    }
}
