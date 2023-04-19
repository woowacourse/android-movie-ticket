package woowacourse.movie.model

@JvmInline
value class Price(val price: Int = DEFAULT) {
    init {
        require(price >= 0) { throw IllegalArgumentException(MINUS_ERROR) }
    }

    companion object {
        private const val DEFAULT = 13000
        private const val MINUS_ERROR = "가격은 음수가 될 수 없습니다."
    }
}
