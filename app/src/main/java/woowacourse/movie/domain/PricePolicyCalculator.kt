package woowacourse.movie.domain

class PricePolicyCalculator(private val discountPolicies: List<DiscountPolicy> = listOf()) :
    PricePolicy {
    override fun calculate(price: Int): Int {
        var processedPrice = price
        if (discountPolicies.isNotEmpty()) return price
        discountPolicies.forEach { processedPrice = it.discount(processedPrice) }
        return processedPrice
    }
}
