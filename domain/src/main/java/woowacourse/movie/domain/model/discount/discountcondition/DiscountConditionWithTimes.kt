package woowacourse.movie.domain.model.discount.discountcondition

import java.time.LocalDateTime

class DiscountConditionWithTimes(private val times: List<Int>) : DiscountCondition {

    init {
        require(times.all { it in MIN_TIME..MAX_TIME })
    }

    override fun isDiscount(dateTime: LocalDateTime): Boolean = times.any {
        it == dateTime.hour
    }

    companion object {
        const val MIN_TIME = 0
        const val MAX_TIME = 23
    }
}
