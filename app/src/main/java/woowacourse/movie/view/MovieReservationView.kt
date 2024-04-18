package woowacourse.movie.view

import woowacourse.movie.model.MovieReservationUiModel

interface MovieReservationView {
    fun showMovieReservation(reservation: MovieReservationUiModel)

    fun updateHeadCount(count: Int)

    fun navigateToReservationResultView(reservationId: Long)
}
