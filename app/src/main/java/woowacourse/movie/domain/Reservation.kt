package woowacourse.movie.domain

import java.time.LocalDateTime

class Reservation(
    val title: String,
    val count: Int,
    val reservedTime: LocalDateTime,
) {
    fun totalPrice(price: Int): Int = price * count
}
