package woowacourse.movie.domain.reservation

import java.io.Serializable

class Seat private constructor(
    val row: Row,
    val column: Column,
) : Serializable {
    val grade: SeatGrade = row.grade
    val price: Int = row.price

    companion object {
        private val seats: MutableMap<Pair<Row, Column>, Seat> = mutableMapOf()

        operator fun invoke(
            row: Int,
            column: Int,
        ): Seat {
            val row = Row(row)
            val column = Column(column)
            return seats.getOrPut(row to column) { Seat(row, column) }
        }

        fun seats(): Set<Seat> =
            (1..5)
                .map { row: Int ->
                    (1..4).map { column: Int ->
                        Seat(row, column)
                    }
                }.flatten()
                .toSet()
    }
}
