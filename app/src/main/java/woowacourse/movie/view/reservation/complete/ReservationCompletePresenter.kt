package woowacourse.movie.view.reservation.complete

import woowacourse.movie.model.ReservationInfo

class ReservationCompletePresenter(
    val view: ReservationCompleteContract.View,
) : ReservationCompleteContract.Presenter {
    private lateinit var reservationInfo: ReservationInfo

    override fun fetchData(getReservationInfo: () -> ReservationInfo?) {
        val result = getReservationInfo()
        if (result == null) {
            view.showErrorDialog()
            return
        }

        reservationInfo = result
        view.updateReservationInfo(reservationInfo)
    }
}
