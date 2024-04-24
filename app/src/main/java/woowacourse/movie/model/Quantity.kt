package woowacourse.movie.model

data class Quantity(var value: Int = MINIMUM_VALUE) {
    init {
        require(MINIMUM_VALUE <= value) { "수량은 0 이상이어야 합니다." }
    }

    fun increase() {
        value += 1
    }

    fun decrease() {
        value = (value - 1).coerceAtLeast(0)
    }

    companion object {
        private const val MINIMUM_VALUE = 0
    }
}
