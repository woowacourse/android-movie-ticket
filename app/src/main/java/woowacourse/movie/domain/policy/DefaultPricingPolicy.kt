package woowacourse.movie.domain.policy

class DefaultPricingPolicy(
    private val price: Int = 13000,
) : PricingPolicy {
    override fun calculatePrice(headCount: Int): Int = headCount * price
}
