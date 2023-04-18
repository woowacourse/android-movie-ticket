package woowacourse.movie.domain.discountpolicy

import java.time.LocalDateTime

interface DateTimeDiscountPolicy : DiscountPolicy {
    val dateTime: LocalDateTime
}
