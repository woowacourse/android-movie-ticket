package woowacourse.movie.ui

import woowacourse.movie.domain.Ticket

data class TicketUiModel(
    val title: String,
    val date: String,
    val time: String,
    val count: String = "",
    val money: Int = 0,
    val seat: String = "",
) {
    companion object {
        fun fromDomain(ticket: Ticket): TicketUiModel =
            TicketUiModel(
                title = ticket.title,
                date = ticket.date.toString(),
                time = ticket.time.toString(),
                count = ticket.count.toString(),
                money = ticket.money,
                seat = ticket.seat.joinToString(", "),
            )
    }
}
