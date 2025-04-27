package woowacourse.movie.domain

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
