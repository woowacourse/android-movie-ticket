package woowacourse.movie.ticket.model

import woowacourse.movie.common_data.MovieDataSource
import woowacourse.movie.reservation.model.Count

object TicketDataResource {
    val ticket =
        listOf(
            Ticket(
                MovieDataSource.movieList[0].title,
                MovieDataSource.movieList[0].screeningDate,
                13_000,
                0,
            ),
        )

    var ticketCount: Count = Count(0)

    var screeningDate = ""

    var screeningTime = ""
}
