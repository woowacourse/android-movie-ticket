package woowacourse.movie.model

class Ticket(private var count: Int = DEFAULT_AMOUNT) {
    init {
        require(count > 0) {
            ERROR_AMOUNT_LESS_THAN_ONE
        }
    }

    fun sub() {
        if (count > 1) {
            count--
        }
    }

    fun add() {
        count++
    }

    fun count(): Int = count

    fun price(): Int = count * TICKET_PRICE

    companion object {
        const val DEFAULT_AMOUNT: Int = 1
        const val TICKET_PRICE: Int = 13_000
        const val ERROR_AMOUNT_LESS_THAN_ONE = "amount 값은 1 이상이어야 합니다."
    }
}
