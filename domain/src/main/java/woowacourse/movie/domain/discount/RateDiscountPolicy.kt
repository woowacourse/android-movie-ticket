package woowacourse.movie.domain.discount

class RateDiscountPolicy(
    discountConditions: List<DiscountCondition>,
    priority: Int,
    private val discountRate: Int
) : DiscountPolicy(discountConditions, priority) {

    override fun calculateDiscountFee(initFee: Money): Money = initFee / 100 * discountRate
}
