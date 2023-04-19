package woowacourse.movie.domain.discount

interface DiscountPolicy {
    fun getDiscountPrice(price: Int): Int
}
