package woowacourse.movie.presenter

import woowacourse.movie.presenter.contract.TicketingResultContract
import woowacourse.movie.view.state.TicketingResult
import woowacourse.movie.view.utils.ErrorMessage

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
) : TicketingResultContract.Presenter {
    override fun initializeTicketingResult(ticketingResult: TicketingResult?) {
        ticketingResult?.let {
            ticketingResultView.assignInitialView(it)
        } ?: ticketingResultView.showToastMessage(ErrorMessage.ERROR_INVALID_SCREENING_ID.value)
    }
}
