package woowacourse.movie.feature.model

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.movie.TicketCount
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel

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
    val seats = Seats(this.capacity)
    this.seats.forEach { seat -> seats.add(seat.toDomain()) }
    return seats
}
