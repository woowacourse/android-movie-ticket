package woowacourse.movie.uiModels.reservation

import woowacourse.movie.model.Reservation
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.seat.Position
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.uiModels.movie.format

fun Reservation.toReservationBrief(movieRepository: MovieRepository): ReservationBrief {
    val movie = movieRepository.getMovie(movieId)
    val title = movie.title.format()
    val positions = positions.joinToString(",") { it.format() }
    val price = price.toString()
    val dateTime = reservedDateTime.format()

    return ReservationBrief(title, positions, dateTime, price)
}
