package woowacourse.movie.presentation.reservation.booking

interface MovieReservationView {
    fun showMovieReservation(reservation: MovieReservationUiModel)

    fun updateHeadCount(count: Int)

    fun navigateToReservationResultView(reservationId: Long)
}
