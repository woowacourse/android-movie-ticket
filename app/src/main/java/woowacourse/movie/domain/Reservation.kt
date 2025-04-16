package woowacourse.movie.domain

import java.time.LocalDateTime

class Reservation(
    val title: String,
    var count: Int,
    val reservedTime: LocalDateTime,
) {
    fun totalPrice(price: Int): Int = price * count

    fun addCount() = Reservation(title, count++, reservedTime)

    fun minusCount() = Reservation(title, count--, reservedTime)

    fun updateReservedTime(time: LocalDateTime) = Reservation(title, count, time)
}
