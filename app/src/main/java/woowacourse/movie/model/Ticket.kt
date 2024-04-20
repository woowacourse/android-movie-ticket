package woowacourse.movie.model

import java.io.Serializable

class Ticket : Serializable {
    private var count: Int = DEFAULT_AMOUNT

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

    companion object {
        const val DEFAULT_AMOUNT: Int = 1
        const val ERROR_AMOUNT_LESS_THAN_ONE = "amount 값은 1 이상이어야 합니다."
    }
}
