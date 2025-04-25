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
        ): Seat = requireNotNull(SEATS[row to col])
    }
}
