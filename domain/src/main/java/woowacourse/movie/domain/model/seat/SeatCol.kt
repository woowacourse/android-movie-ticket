package woowacourse.movie.domain.model.seat

@JvmInline
value class SeatCol(val value: Int) {
    init {
        require(value in MIN_COL_BOUND..MAX_COL_BOUND) { INVALID_COLUMN_RANGE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val MIN_COL_BOUND = 1
        private const val MAX_COL_BOUND = 4

        private const val INVALID_COLUMN_RANGE_EXCEPTION_MESSAGE = "올바르지 않은 열 범위입니다."
    }
}
