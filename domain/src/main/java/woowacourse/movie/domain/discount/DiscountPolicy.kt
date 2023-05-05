package woowacourse.movie.domain.discount

import java.time.LocalDateTime

abstract class DiscountPolicy(
    private val discountConditions: List<DiscountCondition>,
    val priority: Int
) {

    fun getDiscountedFee(screeningDateTime: LocalDateTime, initFee: Money): Money =
        if (discountConditions.any { it.isSatisfiedBy(screeningDateTime) }) {
            val discountFee = calculateDiscountFee(initFee)
            if (initFee < discountFee) Money(0) else initFee - discountFee
        } else initFee

    protected abstract fun calculateDiscountFee(initFee: Money): Money
}
