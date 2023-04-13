package woowacourse.movie.domain

interface Discountable {
    fun discount(money: Int): Int
}
