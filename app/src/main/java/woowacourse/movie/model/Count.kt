package woowacourse.movie.model

data class Count(var amount: Int = DEFAULT_AMOUNT) {
    init {
        require(amount > 0) {
            ERROR_AMOUNT_LESS_THAN_ONE
        }
    }

    fun sub() {
        if (amount > 1) {
            amount--
        }
    }

    fun add() {
        amount++
    }

    fun price(): Int = amount * TICKET_PRICE

    companion object {
        const val DEFAULT_AMOUNT: Int = 1
        const val TICKET_PRICE: Int = 13_000
        const val ERROR_AMOUNT_LESS_THAN_ONE = "amount 값은 1 이상이어야 합니다."
    }
}
