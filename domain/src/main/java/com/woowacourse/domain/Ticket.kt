package com.woowacourse.domain

class Ticket {
    var price: Int = TICKET_PRICE
        private set

    fun getTicketPrice(date: String, time: String): Int {
        calculateMovieDaySale(date)
        calculateTimeBasedSale(time)
        return price
    }

    private fun calculateMovieDaySale(date: String) {
        val day = date.takeLast(DAY).toInt()
        if (day in MOVIE_DAYS) price = (price * MOVIE_DAY_DISCOUNT_RATE).toInt()
    }

    private fun calculateTimeBasedSale(time: String) {
        val hour = time.take(HOUR).toInt()
        if (hour < EARLY_BIRD_TIME || hour >= LATE_NIGHT_TIME) price -= TIME_BASED_DISCOUNT
    }

    companion object {
        private const val TICKET_PRICE = 13_000
        private const val DAY = 2
        private const val HOUR = 2
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val MOVIE_DAY_DISCOUNT_RATE = 0.9
        private const val EARLY_BIRD_TIME = 11
        private const val LATE_NIGHT_TIME = 20
        private const val TIME_BASED_DISCOUNT = 2_000
    }
}
