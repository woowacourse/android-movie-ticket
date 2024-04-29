package woowacourse.movie.presenter.contract

import woowacourse.movie.view.state.TicketingResult

interface TicketingResultContract {
    interface View {
        fun assignInitialView(ticketingResult: TicketingResult)

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeTicketingResult(ticketingResult: TicketingResult?)
    }
}
