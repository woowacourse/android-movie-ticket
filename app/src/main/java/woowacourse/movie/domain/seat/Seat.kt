package woowacourse.movie.domain.seat

import java.io.Serializable

class Seat private constructor(
    val row: Int,
    val col: Int,
) : Serializable {
    val grade: SeatGrade = SeatGrade.of(row)
    val price: Int = grade.price

    companion object {
        private const val ROW_COUNT = 5
        private const val COL_COUNT = 4
        private const val ERROR_INVALID_SEAT = "좌석은 5행 4열로 구성되어 있습니다."

        private val SEATS: Map<Pair<Int, Int>, Seat> =
            buildMap {
                for (row in 0 until ROW_COUNT) {
                    for (col in 0 until COL_COUNT) {
                        put(row to col, Seat(row, col))
                    }
                }
            }

        fun from(
            row: Int,
            col: Int,
        ): Seat = requireNotNull(SEATS[row to col]) { ERROR_INVALID_SEAT }
    }
}
