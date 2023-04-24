package com.woowacourse.domain.ticket

@JvmInline
value class Ticket(private val price: Int) {
    fun getCalculatedTicketPrice(date: String, time: String): Int {
        var amount = price
        amount = calculateMovieDaySale(amount, date)
        amount = calculateTimeBasedSale(amount, time)
        return amount
    }

    private fun calculateMovieDaySale(amount: Int, date: String): Int {
        val day = date.split(SEPARATOR).last().toInt()
        if (day in MOVIE_DAYS) {
            return (amount * MOVIE_DAY_DISCOUNT_RATE).toInt()
        }
        return amount
    }

    private fun calculateTimeBasedSale(amount: Int, time: String): Int {
        val hour = time.take(HOUR).toInt()
        if (hour < EARLY_BIRD_TIME || hour >= LATE_NIGHT_TIME) {
            return amount - TIME_BASED_DISCOUNT
        }
        return amount
    }

    companion object {
        private const val SEPARATOR = "."
        private const val HOUR = 2
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val MOVIE_DAY_DISCOUNT_RATE = 0.9
        private const val EARLY_BIRD_TIME = 11
        private const val LATE_NIGHT_TIME = 20
        private const val TIME_BASED_DISCOUNT = 2_000
    }
}
