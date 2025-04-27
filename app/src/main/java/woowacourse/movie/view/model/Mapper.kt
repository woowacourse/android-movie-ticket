package woowacourse.movie.view.model

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.view.model.MovieListItem.MovieUiModel

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(title, startDate, endDate, runningTime, R.drawable.harry_potter_poster)
}

fun MovieUiModel.toDomain(): Movie {
    return Movie(title, startDate, endDate, runningTime)
}

fun Ticket.toUiModel(): TicketUiModel {
    return TicketUiModel(movie.toUiModel(), showtime, count.value)
}

fun TicketUiModel.toDomain(): Ticket {
    return Ticket(movie.toDomain(), showtime, TicketCount.of(count))
}

fun Seat.toUiModel(): SeatUiModel {
    return SeatUiModel(row, col)
}

fun SeatUiModel.toDomain(): Seat {
    return Seat(row, col)
}

fun Seats.toUiModel(): SeatsUiModel {
    return SeatsUiModel(seats.size, seats.map { seat -> seat.toUiModel() })
}

fun SeatsUiModel.toDomain(): Seats {
    return Seats(seats.size).apply {
        seats.forEach { seat -> add(seat) }
    }
}
