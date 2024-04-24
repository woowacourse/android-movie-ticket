package woowacourse.movie.moviereservation

interface MovieReservationView {
    fun showMovieReservation(reservation: MovieReservationUiModel)

    fun updateHeadCount(updatedCount: HeadCountUiModel)

    fun navigateToReservationResultView(reservationId: Long)

    fun showScreeningMovieError()

    fun showCantDecreaseError(minCount: Int)
}
