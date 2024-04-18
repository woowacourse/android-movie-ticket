package woowacourse.movie.presenter

import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.view.ReservationResultView

class ReservationResultPresenter(
    id: Long,
    private val repository: MovieRepository,
    private val view: ReservationResultView,
) {
    init {
        val reservationResult = repository.movieReservationById(id).toUiModel()
        view.showResult(reservationResult)
    }
}
