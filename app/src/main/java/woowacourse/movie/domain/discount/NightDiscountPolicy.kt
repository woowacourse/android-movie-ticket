package woowacourse.movie.domain.discount

import woowacourse.movie.Movie
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import java.time.LocalDateTime

class NightDiscountPolicy : DiscountPolicy {
    override fun discount(
        originMoney: Money,
        count: Count,
        movie: Movie,
        dateTime: LocalDateTime
    ): Money {
        return when (dateTime.hour) {
            in TIME_NIGHT -> (originMoney - NIGHT_DISCOUNT * count)
            else -> originMoney
        }
    }

    companion object {
        private val TIME_NIGHT = listOf(0, 20, 21, 22, 23)
        private val NIGHT_DISCOUNT = Money(2000)
    }
}
