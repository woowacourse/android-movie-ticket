package woowacourse.movie.domain.discount.discountcondition

import java.time.LocalDateTime

interface DateTimeDiscountCondition {

    fun isDiscount(dateTime: LocalDateTime): Boolean
}
