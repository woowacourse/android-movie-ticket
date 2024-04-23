package woowacourse.movie.movieDetail

import android.content.Intent
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.purchaseConfirmation.PurchaseConfirmationActivity

@Suppress("DEPRECATION")
class MovieDetailPresenter(private val view: MovieDetailView, intent: Intent) {
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
