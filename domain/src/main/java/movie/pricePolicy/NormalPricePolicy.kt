package movie.pricePolicy

import data.PricePolicyInfo

class NormalPricePolicy() : PricePolicy {
    private val policyList = listOf(
        MovieDayPricePolicy(0.9),
        EarlyMorningPricePolicy(2000, 11),
        EveningPricePolicy(2000, 20),
    )

    override fun calculatePrice(price: PricePolicyInfo): PricePolicyInfo =
        policyList.fold(price) { acc, pricePolicy -> pricePolicy.calculatePrice(acc) }
}
