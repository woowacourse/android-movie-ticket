package woowacourse.movie.domain.policy

interface DiscountCondition {
    fun isDiscount(): Boolean
}
