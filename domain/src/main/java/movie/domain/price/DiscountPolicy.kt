package movie.domain.price

interface DiscountPolicy {
    fun discount(price: Int): Int
}
