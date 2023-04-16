package woowacourse.movie.ui.dto

import java.io.Serializable
import java.time.LocalDateTime

fun mapToUITicketTime(ticketTime: woowacourse.movie.domain.TicketTime): TicketTime {
    return TicketTime(
        ticketTime.dateTime
    )
}

data class TicketTime(val dateTime: LocalDateTime) : Serializable
