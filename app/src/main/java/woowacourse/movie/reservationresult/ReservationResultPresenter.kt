package woowacourse.movie.reservationresult

import woowacourse.movie.repository.MovieRepository

class ReservationResultPresenter(
    private val repository: MovieRepository,
    private val view: ReservationResultView,
) {
    fun loadReservationResult(reservationId: Long) {
        val reservationResult =
            repository.movieReservationById(reservationId).toReservationResultUiModel()
        view.showResult(reservationResult)
    }
}
