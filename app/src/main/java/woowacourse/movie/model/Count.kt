package woowacourse.movie.model

class Count(value: Int = INITIAL_COUNT) {
    var value: Int = value
        private set

    init {
        require(value >= INITIAL_COUNT) { ERROR_NON_POSITIVE_NUMBER }
    }

    fun increase() = value++

    fun decrease() {
        if (value > 1) value--
    }

    companion object {
        private const val INITIAL_COUNT = 1
        private const val ERROR_NON_POSITIVE_NUMBER = "구매 티켓은 1개 이상이어야 합니다."
    }
}
