package woowacourse.movie.presenter.movieReservationResult

import woowacourse.movie.view.model.SeatsUiModel
import woowacourse.movie.view.model.TicketUiModel

interface MovieReservationResultContract {
    interface View {
        fun showReservationInfo(
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        )
    }

    interface Presenter {
        fun loadReservationInfo()
    }
}
