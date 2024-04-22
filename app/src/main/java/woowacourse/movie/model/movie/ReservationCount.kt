package woowacourse.movie.model.movie

class ReservationCount(val count: Int = DEFAULT_VALUE) {
    init {
        require(count in MIN_VALUE..MAX_VALUE) { INVALID_COUNT_MESSAGE }
    }

    operator fun dec(): ReservationCount {
        val count = count - OFFSET_VALUE
        return ReservationCount(count.coerceAtLeast(MIN_VALUE))
    }

    operator fun inc(): ReservationCount {
        val count = count + OFFSET_VALUE
        return ReservationCount(count.coerceAtMost(MAX_VALUE))
    }

    companion object {
        const val DEFAULT_VALUE = 1
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 50
        private const val OFFSET_VALUE = 1
        private const val INVALID_COUNT_MESSAGE = "예매 인원은 ${MIN_VALUE}에서 $MAX_VALUE 사이여야 합니다."
    }
}
