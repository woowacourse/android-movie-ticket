package woowacourse.movie.domain

import java.time.LocalDateTime

class ScreeningTimeDiscountCondition(private val timeRanges: List<TimeRange>) : DiscountCondition {
    override fun isSatisfiedBy(screeningDateTime: LocalDateTime): Boolean =
        timeRanges.any { screeningDateTime.toLocalTime() in it }
}
