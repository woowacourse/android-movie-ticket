package woowacourse.movie.domain

import java.time.LocalDateTime

class ScreeningTimeDiscountCondition(private val timeRanges: List<TimeRange>) : DiscountCondition {
    override fun isSatisfiedBy(dateTime: LocalDateTime): Boolean =
        timeRanges.any { dateTime.toLocalTime() in it }
}
