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
    val movie = theater?.movie
}
