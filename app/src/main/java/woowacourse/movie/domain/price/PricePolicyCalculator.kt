package woowacourse.movie.domain.price

class PricePolicyCalculator(private val discountPolicies: List<DiscountPolicy> = listOf()) :
    PricePolicy {
    override fun totalPriceCalculate(ticketPrice: Int, ticketCount: Int): Int =
        discountCalculate(ticketPrice) * ticketCount

    override fun discountCalculate(price: Int): Int {
        var processedPrice = price
        if (discountPolicies.isNotEmpty()) return price
        discountPolicies.forEach { processedPrice = it.discount(processedPrice) }
        return processedPrice
    }
}
