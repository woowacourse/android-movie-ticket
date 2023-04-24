package woowacourse.movie.domain.price.discount.runningpolicy

import woowacourse.movie.domain.price.discount.partialpolicy.DiscountPolicy

interface CompoundPolicy {
    fun getDiscountPolicies(): MutableList<DiscountPolicy>
}
