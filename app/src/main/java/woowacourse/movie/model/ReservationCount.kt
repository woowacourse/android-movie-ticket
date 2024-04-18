package woowacourse.movie.model

class ReservationCount(val count: Int = DEFAULT_VALUE) {
    init {
        require(count in MIN_VALUE..MAX_VALUE) { INVALID_COUNT_MESSAGE }
    }

    operator fun dec(): ReservationCount {
        if (count == MIN_VALUE) return this
        return ReservationCount(count - OFFSET_VALUE)
    }

    operator fun inc(): ReservationCount {
        if (count == MAX_VALUE) return this
        return ReservationCount(count + OFFSET_VALUE)
    }

    companion object {
        private const val DEFAULT_VALUE = 1
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 50
        private const val OFFSET_VALUE = 1
        private const val INVALID_COUNT_MESSAGE = "예매 인원은 ${MIN_VALUE}에서 $MAX_VALUE 사이여야 합니다."
    }
}
