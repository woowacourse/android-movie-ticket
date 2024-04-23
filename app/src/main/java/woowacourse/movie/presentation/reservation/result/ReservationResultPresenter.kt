package woowacourse.movie.presentation.reservation.result

import woowacourse.movie.presentation.reservation.booking.toUiModel
import woowacourse.movie.repository.MovieRepository

class ReservationResultPresenter(
    private val repository: MovieRepository,
    private val view: ReservationResultView,
) {
    fun loadReservationResult(id: Long) {
        repository.movieReservationById(id).onSuccess {
            view.showResult(it.toUiModel())
        }.onFailure {
            view.showErrorView()
        }
    }
}
