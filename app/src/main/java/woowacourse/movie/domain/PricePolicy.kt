package woowacourse.movie.domain

interface PricePolicy {

    fun totalPriceCalculate(ticketPrice: Int, ticketCount: Int): Int

    fun discountCalculate(price: Int): Int
}
