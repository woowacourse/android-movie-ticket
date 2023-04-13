package woowacourse.movie.domain

import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import java.time.LocalDateTime

class DiscountCalculator {
    fun discount(count: Count, dateTime: LocalDateTime): Money {
        val money = when (dateTime.dayOfMonth) {
            10, 20, 30 -> TICKET_MONEY * MOVIE_DAY_DISCOUNT
            else -> TICKET_MONEY
        }
        return when (dateTime.hour) {
            in TIME_MORNING_NIGHT -> (money - TIME_DISCOUNT) * count
            else -> money * count
        }
    }

    companion object {
        private val TICKET_MONEY = Money(13000)
        private const val MOVIE_DAY_DISCOUNT = 0.9f
        private val TIME_DISCOUNT = Money(2000)
        private val TIME_MORNING_NIGHT = listOf(0, 9, 10, 11, 20, 21, 22, 23)
    }
}
