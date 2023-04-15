package movie.pricePolicy

interface PricePolicy {
    operator fun invoke(price: PricePolicyInfo): PricePolicyInfo
}
