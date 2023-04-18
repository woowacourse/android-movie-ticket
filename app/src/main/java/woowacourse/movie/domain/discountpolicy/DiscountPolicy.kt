package woowacourse.movie.domain.discountpolicy

interface DiscountPolicy {
    fun discount(price: Int): Int
}
