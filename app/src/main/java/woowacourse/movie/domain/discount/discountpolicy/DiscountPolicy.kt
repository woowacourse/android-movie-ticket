package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.model.Money

interface DiscountPolicy {
    fun discount(price: Money): Money
}
