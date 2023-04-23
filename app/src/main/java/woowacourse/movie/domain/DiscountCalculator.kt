package woowacourse.movie.domain

import woowacourse.movie.entity.Money
import java.time.LocalDateTime

class DiscountCalculator {
    fun discount(ticketMoney: Money, dateTime: LocalDateTime): Money {
        val money = when (dateTime.dayOfMonth) {
            in MOVIE_DAYS -> ticketMoney * MOVIE_DAY_DISCOUNT
            else -> ticketMoney
        }
        return when (dateTime.hour) {
            in TIME_MORNING_NIGHT -> money - TIME_DISCOUNT
            else -> money
        }
    }

    companion object {
        private const val MOVIE_DAY_DISCOUNT = 0.9f
        private val TIME_DISCOUNT = Money(2000)
        private val TIME_MORNING_NIGHT = listOf(0, 9, 10, 11, 20, 21, 22, 23)
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}
