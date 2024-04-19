package woowacourse.movie.presenter

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MovieDetailActivityPresenter(
    intent: Intent,
    private val view: MovieDetailContract.View,
) : MovieDetailContract.Presenter {
    private var ticketNum = 1
    private val theater: Theater =
        intent.getSerializableExtra("Theater", Theater::class.java) ?: Theater.default

    // TODO: have to notify that something went wrong and go back to movie selection
    // e.g. view.notifyException()
    init {
        view.displayMovie(theater.movie)
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
        view.navigateToPurchaseConfirmation(Reservation(theater, ticketNum))
    }
}
