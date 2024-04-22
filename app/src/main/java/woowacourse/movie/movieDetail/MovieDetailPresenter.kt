// MovieDetailActivityPresenter.kt
package woowacourse.movie.movieDetail

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.purchaseConfirmation.PurchaseConfirmationActivity
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
