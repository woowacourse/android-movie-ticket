package woowacourse.movie.movieDetail// woowacourse.movie.movieDetail.MovieDetailView.kt
import android.content.Context
import android.content.Intent

interface MovieDetailView {
    fun navigateToPurchaseConfirmation(intent: Intent)
    fun onTicketCountChanged(ticketNum: Int)
    fun getContext(): Context
}
