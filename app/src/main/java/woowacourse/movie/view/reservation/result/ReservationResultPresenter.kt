package woowacourse.movie.view.reservation.result

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo

class ReservationResultPresenter(
    private var view: ReservationResultContract.View,
    private val context: Context,
) : ReservationResultContract.Presenter {
    override fun loadReservationInfo(reservationInfo: ReservationInfo?) {
        if (reservationInfo == null || reservationInfo.title.isNullOrEmpty()) {
            view.showMessage(context.getString(R.string.invalid_message))
            return
        }

        view.showReservationResult(reservationInfo)
    }
}
