package woowacourse.movie.presenter.contract

import woowacourse.movie.view.state.TicketingResult
import woowacourse.movie.view.utils.ErrorMessage

interface TicketingResultContract {
    interface View {
        fun assignInitialView(ticketingResult: TicketingResult)

        fun showToastMessage(error: ErrorMessage)
    }

    interface Presenter {
        fun initializeTicketingResult(ticketingResult: TicketingResult?)
    }
}
