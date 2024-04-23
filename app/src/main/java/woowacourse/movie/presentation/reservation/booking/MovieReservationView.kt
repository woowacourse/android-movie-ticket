package woowacourse.movie.presentation.reservation.booking

interface MovieReservationView {
    fun showMovieReservation(reservation: MovieReservationUiModel)

    fun showErrorView()

    fun updateHeadCount(count: Int)

    fun navigateToReservationResultView(reservationId: Long)
}
