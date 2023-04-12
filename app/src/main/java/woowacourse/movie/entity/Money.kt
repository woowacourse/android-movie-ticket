package woowacourse.movie.entity

@JvmInline
value class Money(val value: Int) {
    init {
        require(value > 0) { MONEY_ERROR_MESSAGE }
    }

    companion object {
        private const val MONEY_ERROR_MESSAGE = "[ERROR} 예매 금액은 0원보다 커야 합니다."
    }
}
