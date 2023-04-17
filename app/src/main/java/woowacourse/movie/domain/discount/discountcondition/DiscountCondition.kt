package woowacourse.movie.domain.discount.discountcondition

import java.time.LocalDateTime

interface DiscountCondition {

    fun isDiscount(dateTime: LocalDateTime): Boolean
}
