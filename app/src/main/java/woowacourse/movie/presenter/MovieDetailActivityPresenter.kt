package woowacourse.movie.presenter

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.`interface`.MovieDetailView
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)

class MovieDetailActivityPresenter(private val view: MovieDetailView, intent: Intent) {
    val theater = intent.getSerializableExtra("Theater", Theater::class.java)
    val movie = theater?.movie

    fun onBuyTicketClicked(ticketNum: Int, intent: Intent) {
        intent.apply {
            putExtra("ticketNum", ticketNum)
            putExtra("Theater", theater)
        }
        view.navigateToPurchaseConfirmation(intent)
    }
}
