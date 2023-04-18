package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.Ticket
import java.io.Serializable

interface DiscountPolicy : Serializable {
    fun discount(ticket: Ticket, price: Price): Price
}
