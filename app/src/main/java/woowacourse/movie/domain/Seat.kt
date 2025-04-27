package woowacourse.movie.domain

class Seat(
    val row: Row,
    val column: Column,
) {
    val price: Int = row.price
}

fun Seat(
    row: Int,
    column: Int,
): Seat = Seat(Row(row), Column(column))

@JvmInline
value class Row(
    private val value: Int,
) {
    init {
        require(value in ROW_VALUE_MIN..ROW_VALUE_MAX) {
            ERROR_MESSAGE_INVALID_VALUE_FORMAT.format(value)
        }
    }

    val price: Int get() = grade.price

    private val grade: SeatGrade
        get() =
            when (value) {
                in 1..2 -> SeatGrade.B
                in 3..4 -> SeatGrade.S
                5 -> SeatGrade.A
                else -> throw IllegalStateException(ERROR_MESSAGE_INVALID_VALUE_FORMAT.format(value))
            }

    companion object {
        private const val ROW_VALUE_MIN = 1
        private const val ROW_VALUE_MAX = 5

        private const val ERROR_MESSAGE_INVALID_VALUE_FORMAT =
            "The seats are arranged in 5 rows. But value was %s"
    }
}

@JvmInline
value class Column(
    private val value: Int,
) {
    init {
        require(value in COLUMN_VALUE_MIN..COLUMN_VALUE_MAX) {
            ERROR_MESSAGE_INVALID_VALUE_FORMAT.format(
                value,
            )
        }
    }

    companion object {
        private const val COLUMN_VALUE_MIN = 1
        private const val COLUMN_VALUE_MAX = 4

        private const val ERROR_MESSAGE_INVALID_VALUE_FORMAT =
            "The seats are arranged in 4 columns. But value was %s"
    }
}

enum class SeatGrade(
    val price: Int,
) {
    S(15_000),
    A(12_000),
    B(10_000),
}
