package woowacourse.movie.seat

import android.content.Intent
import woowacourse.movie.model.movieInfo.MovieInfo

interface TheaterSeatContract {
    interface View {
        fun initializeViews(movieInfo: MovieInfo)
        fun navigateToPurchaseConfirmation(intent: Intent)
        fun showToPurchaseConfirmation(intent: Intent)
        fun showDialog()
    }

    interface Presenter {
        fun loadMovieInformation()
        fun onSeatClicked(intent: Intent)
        fun onConfirmationClicked(ticketNum: Int)
        fun onPurchaseClicked(ticketNum: Int)
    }
}
