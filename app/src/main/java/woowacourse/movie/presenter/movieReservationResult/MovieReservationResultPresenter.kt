package woowacourse.movie.presenter.movieReservationResult

import woowacourse.movie.view.model.SeatsUiModel
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_SEATS
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_TICKET
import woowacourse.movie.view.utils.getParcelableCompat

class MovieReservationResultPresenter(
    private val view: MovieReservationResultActivity,
) : MovieReservationResultContract.Presenter {
    override fun loadReservationInfo() {
        val ticket: TicketUiModel = view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET) ?: run { return }
        val seats: SeatsUiModel = view.intent.extras?.getParcelableCompat<SeatsUiModel>(KEY_SEATS) ?: run { return }
        view.showReservationInfo(ticket, seats)
    }
}
