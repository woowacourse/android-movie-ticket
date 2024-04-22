package woowacourse.movie.presenter

import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.view.ReservationResultView

class ReservationResultPresenter(
    private val repository: MovieRepository,
    private val view: ReservationResultView,
) {

    fun loadReservationResult(reservationId:Long) {
        val reservationResult = repository.movieReservationById(reservationId).toUiModel()
        view.showResult(reservationResult)
    }
}
