package woowacourse.movie.purchaseConfirmation

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.theater.Theater

class PurchaseConfirmationActivityPresenter(intent: Intent) {
    private val theater = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("Theater", Theater::class.java)
    } else {
        intent.getSerializableExtra("Theater") as? Theater
    }
    private val numberOfTicket = intent.getIntExtra("ticketNum", 0)
    private val ticketOfCharge = theater?.charge ?: 13000
    val movie = theater?.movie

    fun calculate(): Int {
        return numberOfTicket * ticketOfCharge
    }
}
