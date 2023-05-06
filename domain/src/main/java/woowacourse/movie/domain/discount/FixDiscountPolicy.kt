package woowacourse.movie.domain.discount

class FixDiscountPolicy(
    discountConditions: List<DiscountCondition>,
    priority: Int,
    private val discountFee: Money
) : DiscountPolicy(discountConditions, priority) {

    override fun calculateDiscountFee(initFee: Money): Money = discountFee
}
