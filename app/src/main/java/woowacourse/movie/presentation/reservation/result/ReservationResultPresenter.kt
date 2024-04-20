package woowacourse.movie.presentation.reservation.result

import woowacourse.movie.presentation.reservation.booking.toUiModel
import woowacourse.movie.repository.MovieRepository

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
