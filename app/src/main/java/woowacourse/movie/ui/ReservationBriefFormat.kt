package woowacourse.movie.ui

import woowacourse.movie.model.Reservation
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.seat.Position
import woowacourse.movie.repository.MovieRepository

fun Reservation.toBrief(movieRepository: MovieRepository): ReservationBrief {
    val movie = movieRepository.getMovie(movieId)

    val title = movie.movieDetail.title.format()
    val positions = this.positions.map { it.format() }
    val price = this.price.toString()
    val dateTime = this.reservedDateTime.format()

    return ReservationBrief(title, positions, dateTime, price)
}

fun Position.format(): String {
    val row: Char = ((this.y - 1) + 'A'.code).toChar()
    val col = this.x
    return "$row$col"
}

internal fun ScreeningDateTime.format(): String {
    val dateTime = this.dateTime
    return "${dateTime.monthValue}월 ${dateTime.dayOfMonth}일 ${dateTime.hour}시"
}
