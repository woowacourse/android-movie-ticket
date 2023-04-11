package woowacourse.movie.domain

import java.io.Serializable

class Ticket(val price: Int = DEFAULT_PRICE) : Serializable {
    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}
