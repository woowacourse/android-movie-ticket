package woowacourse.movie.domain

import java.time.LocalDateTime

class TimeDiscountPolicy : DiscountPolicy {
    override fun discountPrice(price: Int): Int {
        return price - 2000
    }

    override fun checkPolicy(date: LocalDateTime): Boolean {
        if (date.hour !in 12..19) return true
        return false
    }
}
