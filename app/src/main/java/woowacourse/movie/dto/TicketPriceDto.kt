package woowacourse.movie.dto

import domain.policy.DiscountPolicy
import java.io.Serializable

data class TicketPriceDto(
    val price: Int = TICKET_PRICE,
    val discountPolicies: List<DiscountPolicy>,
) : Serializable {

    constructor(discountPolicies: List<DiscountPolicy>) : this(TICKET_PRICE, discountPolicies)

    companion object {
        const val TICKET_PRICE = 13000
    }
}
