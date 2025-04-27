package woowacourse.movie.domain

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Seat

        if (row != other.row) return false
        if (column != other.column) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row.hashCode()
        result = 31 * result + column.hashCode()
        return result
    }
}
