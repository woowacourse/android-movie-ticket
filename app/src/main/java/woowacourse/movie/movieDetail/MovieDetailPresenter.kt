package woowacourse.movie.movieDetail

import android.content.Intent
import woowacourse.movie.model.theater.Theater

@Suppress("DEPRECATION")
class MovieDetailPresenter(
    private val view : MovieDetailContract.View,
    intent: Intent
) : MovieDetailContract.Presenter {
    private val theater = intent.getSerializableExtra("Theater") as? Theater
    val movie = theater?.movie
    override fun load() {
        if (movie != null) {
            view.initializeViews(movie)
        }
    }

    override fun onBuyTicketClicked(intent: Intent) {
        view.navigateToPurchaseConfirmation(intent)
    }


    override fun onTicketPlusClicked(ticketNum: Int) {
        val newTicketNum = ticketNum + 1
        view.onTicketCountChanged(newTicketNum)
    }

    override fun onTicketMinusClicked(ticketNum: Int) {
        if (ticketNum > 0) {
            val newTicketNum = ticketNum - 1
            view.onTicketCountChanged(newTicketNum)
        }
    }

    override fun getTheater(): Theater? {
        return this.theater
    }
}
