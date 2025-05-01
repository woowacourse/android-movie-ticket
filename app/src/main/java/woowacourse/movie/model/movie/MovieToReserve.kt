package woowacourse.movie.model.movie

import woowacourse.movie.model.ticket.TicketCount
import java.io.Serializable

data class MovieToReserve(
    val id: Long,
    val title: String,
    val movieDate: MovieDate,
    val movieTime: MovieTime,
    val ticketCount: TicketCount,
) : Serializable
