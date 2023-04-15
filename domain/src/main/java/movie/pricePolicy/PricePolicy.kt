package movie.pricePolicy

import java.io.Serializable

interface PricePolicy : Serializable {
    operator fun invoke(price: PricePolicyInfo): PricePolicyInfo
}
