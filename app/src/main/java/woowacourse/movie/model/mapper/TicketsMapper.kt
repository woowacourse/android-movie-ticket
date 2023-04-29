package woowacourse.movie.model.mapper

import com.example.domain.model.Tickets
import woowacourse.movie.model.TicketsState

fun TicketsState.asDomain(): Tickets = Tickets(
    tickets.map { it.asDomain() }
)

fun Tickets.asPresentation(): TicketsState = TicketsState(
    movie.asPresentation(),
    dateTime,
    getTotalDiscountedMoney().asPresentation(),
    tickets.map { it.asPresentation() }
)
