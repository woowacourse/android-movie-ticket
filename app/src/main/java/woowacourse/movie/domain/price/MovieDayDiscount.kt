package woowacourse.movie.domain.price

class MovieDayDiscount : DiscountPolicy {
    override fun discount(price: Int): Int = (price * 0.9).toInt()
}
