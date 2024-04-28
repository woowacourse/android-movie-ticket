package woowacourse.movie.model.pricing

class TierPricePolicy(private val tier: Tier) : PricePolicy {
    override fun getPrice(): Int =
       when (tier) {
            Tier.B -> B_TIER_PRICE
            Tier.S -> S_TIER_PRICE
            else -> A_TIER_PRICE
        }

    companion object {
        private const val B_TIER_PRICE = 10000
        private const val S_TIER_PRICE = 15000
        private const val A_TIER_PRICE = 12000
    }
}
