package woowacourse.movie.domain

import java.time.LocalDateTime

interface DiscountCondition {
    fun isSatisfiedBy(screeningDateTime: LocalDateTime): Boolean
}
