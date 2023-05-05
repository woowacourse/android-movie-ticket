package woowacourse.movie.domain.discount

import java.time.LocalDateTime

class DayDiscountCondition(private val days: List<Int>) : DiscountCondition {
    override fun isSatisfiedBy(screeningDateTime: LocalDateTime): Boolean =
        screeningDateTime.dayOfMonth in days
}
