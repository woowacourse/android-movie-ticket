package woowacourse.movie.movieDetail

import android.content.Intent
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.theater.Theater

interface MovieDetailContract {
    interface View {
        fun initializeViews(movieInfo: MovieInfo)
        fun navigateToPurchaseConfirmation(intent: Intent)
        fun onTicketCountChanged(ticketNum: Int)
        fun showToast(message: String)
        fun updateTimeAdapter(times: List<String>)
    }

    interface Presenter {
        fun load()
        fun onTicketPlusClicked(currentTicketNum: Int)
        fun onTicketMinusClicked(currentTicketNum: Int)
        fun getTheater(): Theater?
        fun updateTimeSpinner(date: String)
    }
}
