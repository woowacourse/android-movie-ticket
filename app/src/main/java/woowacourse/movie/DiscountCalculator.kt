package woowacourse.movie

import java.time.LocalDateTime

class DiscountCalculator {
    fun discount(count: Int, dateTime: LocalDateTime): Int {
        var money = count * TICKET_MONEY
        money = when (dateTime.dayOfMonth) {
            10, 20, 30 -> (money * MOVIE_DAY_DISCOUNT).toInt()
            else -> money
        }
        return when (dateTime.hour) {
            in TIME_MORNING_NIGHT -> money - TIME_DISCOUNT
            else -> money
        }
    }

    companion object {
        private const val TICKET_MONEY = 13000
        private const val MOVIE_DAY_DISCOUNT = 0.9
        private const val TIME_DISCOUNT = 2000
        private val TIME_MORNING_NIGHT = listOf(0, 9, 10, 11, 20, 21, 22, 23)
    }
}
