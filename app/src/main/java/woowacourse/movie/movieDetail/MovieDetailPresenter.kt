package woowacourse.movie.movieDetail

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.theater.Theater

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    intent: Intent
) : MovieDetailContract.Presenter {
    private val theater = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("Theater", Theater::class.java)
    } else {
        @Suppress("DEPRECATION")
        TODO("VERSION.SDK_INT < TIRAMISU")
    }
    val movie = theater?.movie
    override fun load() {
        movie?.let { view.initializeViews(it) }
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
