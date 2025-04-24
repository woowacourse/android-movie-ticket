package woowacourse.movie.domain.model

@JvmInline
value class Price(val value: Int) {
    init {
        require(value >= 0) { VALIDATE_TICKET_PRICE }
    }

    companion object {
        private const val VALIDATE_TICKET_PRICE = "티켓의 가격은 0원 이상입니다. "
    }
}
