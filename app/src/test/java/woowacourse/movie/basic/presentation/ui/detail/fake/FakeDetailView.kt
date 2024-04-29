package woowacourse.movie.basic.presentation.ui.detail.fake

import woowacourse.movie.domain.model.ScreenDate
import woowacourse.movie.domain.model.ScreenView.Screen
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.model.ReservationInfo
import woowacourse.movie.presentation.ui.detail.DetailContract

class FakeDetailView : DetailContract.View {
    var screen: Screen? = null
    var isBack = false
    var ticketCount = 1
    var reservationInfo: ReservationInfo? = null
    var toastMessage: MessageType? = null
    var snackBarMessage: MessageType? = null
    var throwable: Throwable? = null

    override fun showScreen(screen: Screen) {
        this.screen = screen
    }

    override fun showDateSpinnerAdapter(screenDates: List<ScreenDate>) {}

    override fun showTimeSpinnerAdapter(screenDate: ScreenDate) {}

    override fun showTicket(count: Int) {
        ticketCount = count
    }

    override fun navigateToSeatSelection(reservationInfo: ReservationInfo) {
        this.reservationInfo = reservationInfo
    }

    override fun back() {
        isBack = true
    }

    override fun showToastMessage(messageType: MessageType) {
        toastMessage = messageType
    }

    override fun showToastMessage(e: Throwable) {
        throwable = e
    }

    override fun showSnackBar(messageType: MessageType) {
        snackBarMessage = messageType
    }

    override fun showSnackBar(e: Throwable) {
        throwable = e
    }
}
