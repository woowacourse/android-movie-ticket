package woowacourse.movie.domain.discount.discountpolicy

import java.time.LocalDateTime

interface DateTimeDiscountPolicy : DiscountPolicy {
    val dateTime: LocalDateTime
}
