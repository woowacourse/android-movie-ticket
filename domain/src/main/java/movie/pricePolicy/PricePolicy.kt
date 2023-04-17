package movie.pricePolicy

interface PricePolicy {
    fun calculatePrice(price: PricePolicyInfo): PricePolicyInfo
}
