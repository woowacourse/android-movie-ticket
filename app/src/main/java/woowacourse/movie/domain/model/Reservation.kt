package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val title: String,
    val reservedTime: LocalDateTime,
    private var tickets: Tickets?,
    purchaseType: PurchaseType = PurchaseType.DEFAULT,
) : Serializable {
    val ticketCount get() = requireNotNull(tickets?.count) { INVALID_TICKETS_NOT_NULL }
    val cancelMinute = purchaseType.cancelTime.minute

    fun initTickets(tickets: Tickets) {
        this.tickets = tickets
    }

    fun totalPrice(): Int = requireNotNull(tickets?.totalPrice()) { INVALID_TICKETS_NOT_NULL }

    fun updateReservedTime(time: LocalDateTime) = Reservation(title, time, tickets)

    companion object {
        private const val INVALID_TICKETS_NOT_NULL = "아직 티켓을 초기화 하지 않았습니다."
    }
}
