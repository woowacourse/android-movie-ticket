package woowacourse.movie.movieDetail

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    intent: Intent
) : MovieDetailContract.Presenter {
    private val theater = intent.getSerializableExtra("Theater", Theater::class.java)
    val movie = theater?.movie
    override fun load() {
        movie?.let { view.initializeViews(it) }
    }

    override fun onSeatConfirmationClicked(intent: Intent) {
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
