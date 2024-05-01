package woowacourse.movie.uimodel.reservation

import woowacourse.movie.model.Reservation
import woowacourse.movie.repository.movie.MovieRepository
import woowacourse.movie.uimodel.movie.format

fun Reservation.toReservationBrief(movieRepository: MovieRepository): ReservationBrief {
    val movie = movieRepository.getMovie(movieId)
    val title = movie.title.format()
    val positions = positions.joinToString(",") { it.format() }
    val price = price.toString()
    val dateTime = reservedDateTime.format()

    return ReservationBrief(title, positions, dateTime, price)
}
