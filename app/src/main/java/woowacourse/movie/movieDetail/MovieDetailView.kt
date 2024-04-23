package woowacourse.movie.movieDetail
import android.content.Context
import android.content.Intent

interface MovieDetailView {
    fun navigateToPurchaseConfirmation(intent: Intent)
    fun onTicketCountChanged(ticketNum: Int)
    fun getContext(): Context
}
