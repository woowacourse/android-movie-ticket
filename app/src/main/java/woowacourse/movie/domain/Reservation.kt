package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val title: String,
    val reservedTime: LocalDateTime,
    private val tickets: Tickets,
) : Serializable {
    val ticketCount get() = tickets.count

    fun totalPrice(): Int = tickets.totalPrice()

    fun plusCount() = Reservation(title, reservedTime, tickets.add(TicketType.DEFAULT))

    fun canMinus(): Boolean = tickets.canMinus()

    fun minusCount() = Reservation(title, reservedTime, tickets.remove(TicketType.DEFAULT))

    fun updateReservedTime(time: LocalDateTime) = Reservation(title, time, tickets)
}
