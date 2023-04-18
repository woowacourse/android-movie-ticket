package movie.domain.price

interface PricePolicy {

    fun totalPriceCalculate(ticketPrice: Int, ticketCount: Int): Int

    fun discountCalculate(price: Int): Int
}
