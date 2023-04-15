package movie.pricePolicy

class NormalPricePolicy() : PricePolicy {
    private val policyList = listOf(
        MovieDayPricePolicy(0.9),
        EarlyMorningPricePolicy(2000, 11),
        EveningPricePolicy(2000, 20),
    )

    override fun invoke(price: PricePolicyInfo): PricePolicyInfo {
        var priceTemp = price
        policyList.forEach {
            priceTemp = it.invoke(priceTemp)
        }
        return priceTemp
    }
}
