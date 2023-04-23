package woowacourse.movie.domain

import java.time.LocalDateTime

class DayDiscountCondition(private val days: List<Int>) : DiscountCondition {
    override fun isSatisfiedBy(dateTime: LocalDateTime): Boolean =
        dateTime.dayOfMonth in days
}
