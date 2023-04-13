package woowacourse.movie.domain.price

class EarlyMorningLateNightDiscount : DiscountPolicy {
    override fun discount(price: Int): Int {
        if (price >= 2000) return (price - 2000)
        return 0
    }
}
