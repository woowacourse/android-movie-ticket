package woowacourse.movie.domain.model.cinema.ticket

import woowacourse.movie.domain.model.cinema.screen.Seat
import java.time.LocalDateTime

class TicketBundle private constructor(
    tickets: List<Ticket>,
) {
    val title: String = tickets.first().title
    val size: Int = tickets.size
    val dateTime: LocalDateTime = tickets.first().reservationDateTime
    val totalPrice: Int = tickets.sumOf { it.price }
    val labels: List<Seat> = tickets.map { it.seat }

    companion object {
        fun bundleOf(tickets: List<Ticket>): TicketBundle {
            require(tickets.isNotEmpty()) { ERROR_TICKET_BUNDLE_EMPTY }
            require(tickets.distinctBy { it.title }.size == SAME_TITLE_TICKET_COUNT) { ERROR_TICKET_BUNDLE_MISMATCHED_TITLES }

            return TicketBundle(tickets)
        }

        const val DEFAULT_TOTAL_PRICE = 0
        private const val SAME_TITLE_TICKET_COUNT = 1
        private const val ERROR_TICKET_BUNDLE_EMPTY = "티켓 묶음은 하나 이상의 티켓이 있어야 합니다"
        private const val ERROR_TICKET_BUNDLE_MISMATCHED_TITLES = "티켓 묶음의 모든 티켓은 동일한 영화여야 합니다"
    }
}
