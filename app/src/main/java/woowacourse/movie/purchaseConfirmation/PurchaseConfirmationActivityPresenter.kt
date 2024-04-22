package woowacourse.movie.purchaseConfirmation

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class PurchaseConfirmationActivityPresenter(intent: Intent) {
    private val theater = intent.getSerializableExtra("Theater", Theater::class.java)
    private val numberOfTicket = intent.getIntExtra("ticketNum", 0)
    private val ticketOfCharge = theater?.charge ?: 13000
    val movie = theater?.movie

    fun calculate(): Int {
        return numberOfTicket * ticketOfCharge
    }
}
