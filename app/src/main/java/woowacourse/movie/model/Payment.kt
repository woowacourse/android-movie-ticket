package woowacourse.movie.model

import java.io.Serializable

class Payment : Serializable {
    fun price(count: Int) = count * TICKET_PRICE

    companion object {
        const val TICKET_PRICE: Int = 13_000
    }
}
