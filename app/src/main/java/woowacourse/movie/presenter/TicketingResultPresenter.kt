package woowacourse.movie.presenter

import woowacourse.movie.presenter.contract.TicketingResultContract
import woowacourse.movie.view.state.TicketingResult

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
) : TicketingResultContract.Presenter {
    override fun initializeTicketingResult(ticketingResult: TicketingResult?) {
        ticketingResult?.let {
            ticketingResultView.assignInitialView(it)
        } ?: ticketingResultView.showToastMessage("존재하지 않는 상영 정보입니다.")
    }
}
