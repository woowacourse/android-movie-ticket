package woowacourse.movie.domain.model.seat

typealias DomainSeatCol = SeatColumn

@JvmInline
value class SeatColumn(val value: Int) {
    init {
        require(value >= MIN_COL_BOUND) { INVALID_COLUMN_RANGE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val MIN_COL_BOUND = 1

        private const val INVALID_COLUMN_RANGE_EXCEPTION_MESSAGE = "올바르지 않은 열 범위입니다."
    }
}
