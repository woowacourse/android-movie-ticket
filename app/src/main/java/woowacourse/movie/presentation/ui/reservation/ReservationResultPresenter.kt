package woowacourse.movie.presentation.ui.reservation

import android.content.Intent

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val intent: Intent,
) : ReservationResultContract.Presenter {
    override fun loadReservationDetails() {
        val title = intent.getStringExtra("title") ?: ""
        val screeningDate = intent.getStringExtra("screeningDate") ?: ""
        val reservationCount = intent.getIntExtra("reservationCount", 0)
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        if (title.isEmpty() || screeningDate.isEmpty()) {
            view.showError("예약 정보를 불러오는데 실패했습니다.")
        } else {
            view.showTitle(title)
            view.showScreeningDate(screeningDate)
            view.showReservationCount(reservationCount)
            view.showTotalPrice(totalPrice)
        }
    }
}
