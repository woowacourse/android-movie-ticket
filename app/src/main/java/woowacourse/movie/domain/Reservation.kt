package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val movie: Movie,
    val reservedTime: LocalDateTime,
    private val _count: TicketCount,
    val seats: Seats = Seats(),
) : Serializable {
    val count = _count.count

    fun totalPrice(): Int = TICKET_PRICE * count

    fun plusCount() = Reservation(movie, reservedTime, _count + 1, seats)

    fun canMinus(): Boolean = _count.canMinus()

    fun minusCount() = Reservation(movie, reservedTime, _count - 1, seats)

    fun updateReservedTime(time: LocalDateTime) = Reservation(movie, time, _count, seats)

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
