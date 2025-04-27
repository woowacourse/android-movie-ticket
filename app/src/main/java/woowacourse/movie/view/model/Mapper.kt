package woowacourse.movie.view.model

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.movie.TicketCount
import woowacourse.movie.domain.theater.Seat
import woowacourse.movie.domain.theater.Theater
import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.SeatUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel

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

fun Theater.toUiModel(): TheaterUiModel {
    return TheaterUiModel(seats.size, seats.map { seat -> seat.toUiModel() })
}

fun TheaterUiModel.toDomain(): Theater {
    val theater = Theater(seats.size)
    this.seats.forEach { seat -> theater.add(seat.toDomain()) }
    return theater
}
