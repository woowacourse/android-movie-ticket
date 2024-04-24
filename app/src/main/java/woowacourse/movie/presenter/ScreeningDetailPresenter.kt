package woowacourse.movie.presenter

import woowacourse.movie.contract.ScreeningDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ScreeningRepository

class ScreeningDetailPresenter(
    private val view: ScreeningDetailContract.View,
    private val repository: ScreeningRepository = PseudoScreeningRepository,
) : ScreeningDetailContract.Presenter {
    // TODO: have to notify that something went wrong and go back to movie selection
    // e.g. view.notifyException()

    override fun loadScreening(screeningId: Int) {
        val screening = repository.getScreening(screeningId) ?: Screening.default
        view.displayScreening(screening)
    }

    override fun plusTicketNum(ticketNum: Int) {
        view.displayTicketNum(ticketNum + 1)
    }

    override fun minusTicketNum(ticketNum: Int) {
        if (ticketNum > 0) view.displayTicketNum(ticketNum - 1)
    }

    override fun purchase(
        screeningId: Int,
        ticketNum: Int,
    ) {
        val screening = repository.getScreening(screeningId) ?: Screening.default
        view.navigateToPurchaseConfirmation(Reservation(screening, ticketNum))
    }
}
