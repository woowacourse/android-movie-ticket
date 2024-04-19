package woowacourse.movie.presenter

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class PurchaseConfirmationActivityPresenter(intent: Intent) {
    private val theater = intent.getSerializableExtra("Theater", Theater::class.java)
    private val numberOfTicket = intent.getIntExtra("ticketNum", 0)
    private val ticketOfCharge = if (theater != null) theater.charge else 13000
    val movie = theater?.movie

    fun calculate(): Int {
        return numberOfTicket * ticketOfCharge
    }
}
