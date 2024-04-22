package woowacourse.movie.moviereservation

import woowacourse.movie.moviereservation.MovieReservationUiModel

interface MovieReservationView {
    fun showMovieReservation(reservation: MovieReservationUiModel)

    fun updateHeadCount(count: Int)

    fun navigateToReservationResultView(reservationId: Long)
}
