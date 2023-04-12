package woowacourse.movie.domain

interface PricePolicy {

    fun calculate(price: Int): Int
}
