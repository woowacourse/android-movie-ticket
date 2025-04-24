package woowacourse.movie.presentation.view.reservation.result

import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.toModel

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
) : ReservationResultContract.Presenter {
    private var reservationInfo: ReservationInfo? = null

    val cancellationTime = ReservationInfo.CANCELLATION_TIME

    override fun fetchDate(getReservationInfo: () -> ReservationInfoUiModel?) {
        getReservationInfo()?.let { info ->
            reservationInfo = info.toModel()
            view.setScreen(info)
            return
        }

        view.showInvalidReservationInfoDialog()
    }
}
