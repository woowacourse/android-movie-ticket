package woowacourse.movie.presentation.ui.detail.fake

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.ui.detail.DetailContract

class FakeDetailView : DetailContract.View {
    var screen: Screen? = null
    var isBack = false
    var ticketCount = 1
    var reservationId: Int? = null
    var toastMessage: MessageType? = null
    var snackBarMessage: MessageType? = null
    var throwable: Throwable? = null

    override fun showScreen(screen: Screen) {
        this.screen = screen
    }

    override fun showTicket(count: Int) {
        ticketCount = count
    }

    override fun navigateToReservation(id: Int) {
        reservationId = id
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
