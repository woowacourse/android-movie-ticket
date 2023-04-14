package woowacourse.movie.domain.model.movie.discount.policy

import woowacourse.movie.domain.model.movie.discount.discountable.Discountable

class MovieDayDiscountPolicy(discountable: Discountable) : DiscountPolicy(discountable) {
    override fun discount(money: Int): Int =
        if (isDiscountable()) (money - money * DISCOUNT_PERCENT).toInt() else money

    companion object {
        private const val DISCOUNT_PERCENT = 0.1
    }
}
