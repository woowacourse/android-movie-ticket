package woowacourse.movie.view.model

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(title, startDate, endDate, runningTime, R.drawable.harry_potter_poster)
}

fun MovieUiModel.toDomain(): Movie {
    return Movie(title, startDate, endDate, runningTime)
}

fun Ticket.toUiModel(): TicketUiModel {
    return TicketUiModel(movie.toUiModel(), showtime, count)
}

fun TicketUiModel.toDomain(): Ticket {
    return Ticket(movie.toDomain(), showtime, count)
}
