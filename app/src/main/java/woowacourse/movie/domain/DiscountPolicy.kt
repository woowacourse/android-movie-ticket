package woowacourse.movie.domain

import java.time.LocalDateTime

interface DiscountPolicy {

    fun discountPrice(price: Int): Int
    fun checkPolicy(date: LocalDateTime): Boolean
}
