package woowacourse.movie.presenter

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.contract.ScreeningDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ScreeningDetailPresenter(
    intent: Intent,
    private val view: ScreeningDetailContract.View,
) : ScreeningDetailContract.Presenter {
    private var ticketNum = 1
    private val screening: Screening =
        intent.getSerializableExtra("Screening", Screening::class.java) ?: Screening.default

    // TODO: have to notify that something went wrong and go back to movie selection
    // e.g. view.notifyException()
    init {
        view.displayMovie(screening.movie)
    }

    override fun plusTicketNum() {
        ticketNum += 1
        view.displayTicketNum(ticketNum)
    }

    override fun minusTicketNum() {
        if (ticketNum > 0) ticketNum -= 1
        view.displayTicketNum(ticketNum)
    }

    fun onBuyButtonClicked() {
        view.navigateToPurchaseConfirmation(Reservation(screening, ticketNum))
    }
}
