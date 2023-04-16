package woowacourse.movie.domain.discount.discountcondition

import java.time.LocalDateTime

class DiscountConditionWithTimes(private val times: List<Int>) : DateTimeDiscountCondition {

    init {
        require(times.all { it in MIN_TIME..MAX_TIME })
    }

    override fun isDiscount(dateTime: LocalDateTime): Boolean = times.any {
        it == dateTime.hour
    }

    companion object {
        const val MIN_TIME = 1
        const val MAX_TIME = 31
    }
}
