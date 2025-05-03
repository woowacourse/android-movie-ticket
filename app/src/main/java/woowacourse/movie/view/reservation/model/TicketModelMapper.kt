package woowacourse.movie.view.reservation.model

import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.movie.model.toDomain
import woowacourse.movie.view.movie.model.toUiModel

fun Ticket.toUiModel(): TicketUiModel =
    TicketUiModel(
        movie = movie.toUiModel(),
        showtime = showtime,
        count = count,
        seats = seats,
    )

fun TicketUiModel.toDomain(): Ticket =
    Ticket(
        movie = movie.toDomain(),
        showtime = showtime,
        count = count,
        seats = seats,
    )
