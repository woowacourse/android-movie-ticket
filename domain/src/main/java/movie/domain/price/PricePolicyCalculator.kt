package movie.domain.price

class PricePolicyCalculator(private val discountPolicies: List<DiscountPolicy> = listOf()) :
    PricePolicy {
    override fun totalPriceCalculate(ticketPrice: Int, ticketCount: Int): Int =
        discountCalculate(ticketPrice) * ticketCount

    override fun discountCalculate(price: Int): Int {
        return discountPolicies.fold(price) { discountedPrice, discountPolicy ->
            discountPolicy.discount(discountedPrice)
        }
    }
}
