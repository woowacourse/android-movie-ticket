package woowacourse.movie.domain.discount

import java.time.LocalDateTime

interface DiscountCondition {
    fun isSatisfiedBy(screeningDateTime: LocalDateTime): Boolean
}
