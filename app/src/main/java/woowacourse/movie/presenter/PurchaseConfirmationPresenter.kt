package woowacourse.movie.presenter

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.theater.Theater

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class PurchaseConfirmationPresenter(
    intent: Intent,
    view: PurchaseConfirmationContract.View,
) : PurchaseConfirmationContract.Presenter {
    private val reservation =
        intent.getSerializableExtra("Reservation", Reservation::class.java)
            ?: Reservation(Theater.default, 1)

    init {
        view.displayReservation(reservation)
    }
}
