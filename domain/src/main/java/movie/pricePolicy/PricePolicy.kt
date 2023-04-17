package movie.pricePolicy

import data.PricePolicyInfo

interface PricePolicy {
    fun calculatePrice(price: PricePolicyInfo): PricePolicyInfo
}
