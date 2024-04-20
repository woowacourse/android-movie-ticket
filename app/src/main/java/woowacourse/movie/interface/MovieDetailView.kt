package woowacourse.movie.`interface`// woowacourse.movie.`interface`.MovieDetailView.kt
import android.content.Context
import android.content.Intent

interface MovieDetailView {
    fun navigateToPurchaseConfirmation(intent: Intent)
    fun onTicketCountChanged(ticketNum: Int)
    fun getContext(): Context
}
