package woowacourse.movie.presenter

import woowacourse.movie.MovieReservationResultContract
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_TICKET
import woowacourse.movie.view.utils.getParcelableCompat

class MovieReservationResultPresenter(
    private val view: MovieReservationResultActivity,
) : MovieReservationResultContract.Presenter {
    override fun loadReservationInfo() {
        val ticket: TicketUiModel = view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET) ?: run { return }
        view.showReservationInfo(ticket)
    }
}
