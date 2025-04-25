package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val movie: Movie,
    val reservedTime: LocalDateTime,
    private val _count: TicketCount,
    val points: Points = Points(),
) : Serializable {
    val count = _count.count

    fun totalPrice(): Int = TICKET_PRICE * count

    fun plusCount() = Reservation(movie, reservedTime, _count + 1, points)

    fun canMinus(): Boolean = _count.canMinus()

    fun minusCount() = Reservation(movie, reservedTime, _count - 1, points)

    fun updateReservedTime(time: LocalDateTime) = Reservation(movie, time, _count, points)

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
