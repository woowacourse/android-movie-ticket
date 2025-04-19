package woowacourse.movie.model.policy

class DefaultPricingPolicy : PricingPolicy {
    override fun calculatePrice(headCount: Int): Int = headCount * DEFAULT_PRICE

    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}
