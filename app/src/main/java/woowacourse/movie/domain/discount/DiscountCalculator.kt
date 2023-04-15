package woowacourse.movie.domain.discount

import woowacourse.movie.Movie
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import java.time.LocalDateTime

class DiscountCalculator {
    private val policy: List<DiscountPolicy> = listOf(
        MovieDayDiscountPolicy(),
        JojoDiscountPolicy(),
        NightDiscountPolicy()
    )

    fun discount(count: Count, movie: Movie, dateTime: LocalDateTime): Money {
        var money = TICKET_MONEY * count
        policy.forEach {
            money = it.discount(money, count, movie, dateTime)
        }
        return money
    }

    companion object {
        private val TICKET_MONEY = Money(13000)
    }
}
