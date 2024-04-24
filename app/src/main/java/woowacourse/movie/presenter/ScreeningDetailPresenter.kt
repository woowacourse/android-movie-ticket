package woowacourse.movie.presenter

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.contract.ScreeningDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ScreeningRepository

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ScreeningDetailPresenter(
    private val view: ScreeningDetailContract.View,
    repository: ScreeningRepository = PseudoScreeningRepository,
) : ScreeningDetailContract.Presenter {

    private var ticketNum = 1
    private val screening: Screening = repository.getScreenings()[0]

    // TODO: have to notify that something went wrong and go back to movie selection
    // e.g. view.notifyException()
    init {
        loadScreening()
    }

    override fun loadScreening() {
        view.displayScreening(screening)
    }

    override fun plusTicketNum() {
        ticketNum += 1
        view.displayTicketNum(ticketNum)
    }

    override fun minusTicketNum() {
        if (ticketNum > 0) ticketNum -= 1
        view.displayTicketNum(ticketNum)
    }

    override fun purchase() {
        view.navigateToPurchaseConfirmation(Reservation(screening, ticketNum))
    }
}
