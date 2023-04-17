package movie.discountpolicy

interface DiscountPolicy {
    fun getDiscountPrice(price: Int): Int
}
