package woowacourse.movie.moviereservation

interface MovieReservationView {
    fun showMovieReservation(reservation: MovieReservationUiModel)

    fun updateHeadCount(count: Int)

    fun navigateToReservationResultView(reservationId: Long)
}
