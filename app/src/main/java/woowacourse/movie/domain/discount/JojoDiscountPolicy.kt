package woowacourse.movie.domain.discount

import woowacourse.movie.Movie
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import java.time.LocalDateTime

class JojoDiscountPolicy : DiscountPolicy {
    override fun discount(
        originMoney: Money,
        count: Count,
        movie: Movie,
        dateTime: LocalDateTime
    ): Money {
        return when (dateTime.hour) {
            in TIME_MORNING -> (originMoney - JOJO_DISCOUNT * count)
            else -> originMoney
        }
    }

    companion object {
        private val TIME_MORNING = listOf(9, 10, 11)
        private val JOJO_DISCOUNT = Money(2000)
    }
}
