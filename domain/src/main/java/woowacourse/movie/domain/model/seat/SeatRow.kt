package woowacourse.movie.domain.model.seat

typealias DomainSeatRow = SeatRow

@JvmInline
value class SeatRow(val value: Int) {
    init {
        require(value >= MIN_ROW_BOUND) { INVALID_ROW_RANGE_EXCEPTION_MESSAGE }
    }

    fun getClass(): SeatClass = SeatClass.get(value)

    companion object {
        private const val MIN_ROW_BOUND = 1

        private const val INVALID_ROW_RANGE_EXCEPTION_MESSAGE = "올바르지 않은 행 범위입니다."
    }
}
