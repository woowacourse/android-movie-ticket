package woowacourse.movie.ui.model

import woowacourse.movie.domain.TicketTime
import java.io.Serializable
import java.time.LocalDateTime

fun mapToTicketTimeModel(ticketTime: TicketTime): TicketTimeModel {
    return TicketTimeModel(
        ticketTime.dateTime
    )
}

data class TicketTimeModel(val dateTime: LocalDateTime) : Serializable
