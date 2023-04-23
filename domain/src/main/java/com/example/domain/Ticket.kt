package com.example.domain

class Ticket(private val seat: Seat) {

    fun getTicketPrice(date: String, time: String): Int {
        var price = seat.getSeatGrade().price
        price = (price * calculateMovieDaySale(date)).toInt()
        price -= calculateTimeBasedSale(time)
        return price
    }

    private fun calculateMovieDaySale(date: String): Double {
        val day = date.takeLast(2).toInt()
        return if (day in SALE_DAYS) MOVIE_DAY_DISCOUNT_RATE else 1.0
    }

    private fun calculateTimeBasedSale(time: String): Int {
        val hour = time.take(2).toInt()
        return if (hour < DISCOUNT_START_TIME || hour >= DISCOUNT_END_TIME) TIME_BASED_DISCOUNT else 0
    }

    fun getSeatName(): String {
        return seat.getSeatName()
    }

    companion object {
        private const val DISCOUNT_START_TIME = 11
        private const val DISCOUNT_END_TIME = 20

        private val SALE_DAYS = listOf(10, 20, 30)
        private const val MOVIE_DAY_DISCOUNT_RATE = 0.9
        private const val TIME_BASED_DISCOUNT = 2000
    }
}
