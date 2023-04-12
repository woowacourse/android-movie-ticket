package woowacourse.movie.domain

interface DiscountPolicy {
    fun discount(price: Int): Int
}
