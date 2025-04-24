package woowacourse.movie.presentation.view.reservation.result

import woowacourse.movie.presentation.model.ReservationInfoUiModel

interface ReservationResultContract {
    interface Presenter {
        fun fetchDate(getReservationInfo: () -> ReservationInfoUiModel?)
    }

    interface View {
        fun setScreen(info: ReservationInfoUiModel)

        fun showInvalidReservationInfoDialog()
    }
}
