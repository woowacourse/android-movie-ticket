package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val title: String,
    var count: Int,
    val reservedTime: LocalDateTime,
) : Serializable {
    fun totalPrice(): Int = TICKET_PRICE * count

    fun addCount() = Reservation(title, ++count, reservedTime)

    fun minusCount(): Reservation {
        if (count == 0) return this
        return Reservation(title, --count, reservedTime)
    }

    fun updateReservedTime(time: LocalDateTime) = Reservation(title, count, time)

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
