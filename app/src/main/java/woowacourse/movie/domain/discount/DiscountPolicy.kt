package woowacourse.movie.domain.discount

import woowacourse.movie.Movie
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import java.time.LocalDateTime

interface DiscountPolicy {
    fun discount(originMoney: Money, count: Count, movie: Movie, dateTime: LocalDateTime): Money
}
