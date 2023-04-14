package woowacourse.movie.domain.model.movie

interface Discountable {
    fun discount(money: Int): Int
}
