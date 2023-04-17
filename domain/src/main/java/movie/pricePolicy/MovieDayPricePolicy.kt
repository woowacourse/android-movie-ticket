package movie.pricePolicy

import data.PricePolicyInfo

class MovieDayPricePolicy(
    private val rateDiscountPrice: Double,
) : PricePolicy {
    override fun calculatePrice(price: PricePolicyInfo): PricePolicyInfo = when {
        isMovieDay(price.reservationDateTime.dayOfMonth) -> price.copy(price = discountMovieDay(price.price))
        else -> price
    }

    private fun discountMovieDay(totalPrice: Int) = (totalPrice * rateDiscountPrice).toInt()
    private fun isMovieDay(day: Int) = day % 10 == 0
}
