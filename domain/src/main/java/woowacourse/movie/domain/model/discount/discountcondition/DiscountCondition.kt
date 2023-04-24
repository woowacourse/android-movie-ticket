package woowacourse.movie.domain.model.discount.discountcondition

import java.time.LocalDateTime

interface DiscountCondition {

    fun isDiscount(dateTime: LocalDateTime): Boolean
}
