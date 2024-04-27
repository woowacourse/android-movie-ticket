package woowacourse.movie.ticket.model

import woowacourse.movie.reservation.model.Count
import woowacourse.movie.seats.model.Seat

object TicketDataResource {
    var movieId: Long = -1

    var price: Int = -1

    var seats = listOf<Seat>()

    var ticketCount: Count = Count(9999)

    var screeningDate = "aaa"

    var screeningTime = "aaa"
}
