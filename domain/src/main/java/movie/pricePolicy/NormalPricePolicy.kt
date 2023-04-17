package movie.pricePolicy

class NormalPricePolicy() : PricePolicy {
    private val policyList = listOf(
        MovieDayPricePolicy(0.9),
        EarlyMorningPricePolicy(2000, 11),
        EveningPricePolicy(2000, 20),
    )

    override fun calculatePrice(price: PricePolicyInfo): PricePolicyInfo {
        var priceTemp = price
        policyList.forEach {
            priceTemp = it.calculatePrice(priceTemp)
        }
        return priceTemp
    }
}
