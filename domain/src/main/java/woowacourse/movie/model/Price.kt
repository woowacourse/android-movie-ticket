package woowacourse.movie.model

@JvmInline
value class Price(val price: Long) {
    init {
        require(price >= MIN_PRICE) {
            "$price - 가격은 ${MIN_PRICE}원 이상 이어야 합니다."
        }
    }

    operator fun times(count: Int): Price {
        return Price(price * count)
    }

    companion object {
        private const val MIN_PRICE = 0L
    }
}
