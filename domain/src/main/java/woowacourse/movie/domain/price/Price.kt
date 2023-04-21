package woowacourse.movie.domain.price

@JvmInline
value class Price(val price: Int = DEFAULT) {
    init {
        require(price >= 0) { MINUS_ERROR }
    }

    operator fun plus(price: Price): Price {
        return Price(this.price + price.price)
    }

    operator fun minus(price: Price): Price {
        return Price(this.price - price.price)
    }

    companion object {
        private const val DEFAULT = 13000
        private const val MINUS_ERROR = "가격은 음수가 될 수 없습니다."
    }
}
