package woowacourse.movie.domain

class Ticket {
    var price: Int = TICKET_PRICE
        private set

    fun getTicketPrice(date: String, time: String): Int {
        calculateMovieDaySale(date)
        calculateTimeBasedSale(time)
        return price
    }

    private fun calculateMovieDaySale(date: String) {
        val day = date.takeLast(2).toInt()
        if (day in SALE_DAYS) price = (price * MOVIE_DAY_DISCOUNT_RATE).toInt()
    }

    private fun calculateTimeBasedSale(time: String) {
        val hour = time.take(2).toInt()
        if (hour < DISCOUNT_START_TIME || hour >= DISCOUNT_END_TIME) price -= TIME_BASED_DISCOUNT
    }

    companion object {
        private const val DISCOUNT_START_TIME = 11
        private const val DISCOUNT_END_TIME = 20

        private const val TICKET_PRICE = 13000
        private val SALE_DAYS = listOf(10, 20, 30)
        private const val MOVIE_DAY_DISCOUNT_RATE = 0.9
        private const val TIME_BASED_DISCOUNT = 2000
    }
}
