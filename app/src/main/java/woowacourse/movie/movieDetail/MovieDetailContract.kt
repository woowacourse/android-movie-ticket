package woowacourse.movie.movieDetail

import android.content.Context
import android.content.Intent
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.theater.Theater

interface MovieDetailContract {
    interface View {
        fun initializeViews(movieInfo: MovieInfo)
        fun navigateToPurchaseConfirmation(intent: Intent)
        fun onTicketCountChanged(ticketNum: Int)
    }

    interface Presenter {
        fun load()
        fun onBuyTicketClicked(intent: Intent)
        fun onTicketPlusClicked(ticketNum: Int)
        fun onTicketMinusClicked(ticketNum: Int)
        fun getTheater(): Theater?
    }
}
