package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val title: String,
    val reservedTime: LocalDateTime,
    val tickets: Tickets?,
    purchaseType: PurchaseType = PurchaseType.DEFAULT,
) : Serializable {
    val ticketCount get() = tickets?.count ?: 0
    val cancelMinute = purchaseType.cancelTime.minute

    fun initTickets(tickets: Tickets) = Reservation(title, reservedTime, tickets)

    fun isEmptyTickets(): Boolean = tickets == null || tickets.isEmpty()

    fun addTicket(ticket: Ticket) = Reservation(title, reservedTime, tickets?.addTicket(ticket))

    fun removeTicket(ticket: Ticket) = Reservation(title, reservedTime, tickets?.removeTicket(ticket))

    fun totalPrice(): Int = requireNotNull(tickets?.totalPrice()) { INVALID_TICKETS_NOT_NULL }

    fun updateReservedTime(time: LocalDateTime) = Reservation(title, time, tickets)

    companion object {
        private const val INVALID_TICKETS_NOT_NULL = "아직 티켓을 초기화 하지 않았습니다."
    }
}
