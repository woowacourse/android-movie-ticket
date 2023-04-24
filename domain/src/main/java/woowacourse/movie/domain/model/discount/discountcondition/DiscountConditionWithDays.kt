package woowacourse.movie.domain.model.discount.discountcondition

import java.time.LocalDateTime

class DiscountConditionWithDays(private val days: List<Int>) : DiscountCondition {

    init {
        require(days.all { it in MIN_DAY..MAX_DAY })
    }

    override fun isDiscount(dateTime: LocalDateTime): Boolean = days.any {
        it == dateTime.dayOfMonth
    }

    companion object {
        const val MIN_DAY = 1
        const val MAX_DAY = 31
    }
}
