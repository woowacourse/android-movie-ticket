package woowacourse.movie.domain

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

    val grade: SeatGrade
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
