package woowacourse.movie.domain.price.pricecalculate

import woowacourse.movie.domain.price.TicketPrice
import woowacourse.movie.domain.price.discount.partialpolicy.DiscountPolicy

class PricePolicyCalculator(private val discountPolicies: List<DiscountPolicy> = listOf()) :
    PricePolicy {

    override fun discountCalculate(price: TicketPrice): TicketPrice {
        var processedPrice = price
        if (discountPolicies.isEmpty()) return price
        discountPolicies.forEach { processedPrice = it.discount(processedPrice) }
        return processedPrice
    }
}
