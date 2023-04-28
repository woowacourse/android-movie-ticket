package woowacourse.movie.model.mapper

import com.example.domain.model.Ticket
import com.example.domain.model.Tickets
import woowacourse.movie.model.TicketsState

fun TicketsState.asDomain(): Tickets = Tickets(
    positions.map {
        Ticket(
            movieState.asDomain(),
            dateTime,
            it.asDomain()
        )
    }
)

fun Tickets.asPresentation(): TicketsState = TicketsState(
    tickets[0].movie.asPresentation(),
    tickets[0].dateTime,
    tickets.map { it.position.asPresentation() }
)
