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
