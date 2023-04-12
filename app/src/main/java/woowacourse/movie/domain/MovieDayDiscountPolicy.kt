package woowacourse.movie.domain

import java.time.LocalDateTime

class MovieDayDiscountPolicy : DiscountPolicy {
    override fun discountPrice(price: Int): Int {
        return (price * 0.9).toInt()
    }

    override fun checkPolicy(date: LocalDateTime): Boolean {
        if (date.dayOfMonth in listOf(10, 20, 30)) return true
        return false
    }
}
