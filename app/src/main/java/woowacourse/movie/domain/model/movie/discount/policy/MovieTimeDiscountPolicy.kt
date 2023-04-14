package woowacourse.movie.domain.model.movie.discount.policy

import woowacourse.movie.domain.model.movie.discount.discountable.Discountable

class MovieTimeDiscountPolicy(discountable: Discountable) : DiscountPolicy(discountable) {
    override fun discount(money: Int): Int =
        if (isDiscountable()) money - DISCOUNT_PRICE else money

    companion object {
        private const val DISCOUNT_PRICE = 2000
    }
}
