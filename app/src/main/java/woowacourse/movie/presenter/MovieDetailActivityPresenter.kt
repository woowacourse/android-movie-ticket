// MovieDetailActivityPresenter.kt
package woowacourse.movie.presenter

import android.content.Intent
import woowacourse.movie.activity.PurchaseConfirmationActivity
import woowacourse.movie.`interface`.MovieDetailView
import woowacourse.movie.model.theater.Theater

@Suppress("DEPRECATION")
class MovieDetailActivityPresenter(private val view: MovieDetailView, intent: Intent) {
    private val theater = intent.getSerializableExtra("Theater") as? Theater
    val movie = theater?.movie

    fun onBuyTicketClicked(ticketNum: Int) {
        val intent = Intent(view.getContext(), PurchaseConfirmationActivity::class.java).apply {
            putExtra("ticketNum", ticketNum)
            putExtra("Theater", theater)
        }
        view.navigateToPurchaseConfirmation(intent)
    }


    fun onTicketPlusClicked(currentTicketNum: Int) {
        val newTicketNum = currentTicketNum + 1
        view.onTicketCountChanged(newTicketNum)
    }

    fun onTicketMinusClicked(currentTicketNum: Int) {
        if (currentTicketNum > 0) {
            val newTicketNum = currentTicketNum - 1
            view.onTicketCountChanged(newTicketNum)
        }
    }
}
