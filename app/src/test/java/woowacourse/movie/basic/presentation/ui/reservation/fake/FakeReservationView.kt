package woowacourse.movie.basic.presentation.ui.reservation.fake

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.ui.reservation.ReservationContract

class FakeReservationView : ReservationContract.View {
    var reservation: Reservation? = null
    var isBack = false
    var toastMessage: MessageType? = null
    var snackBarMessage: MessageType? = null
    var throwable: Throwable? = null

    override fun showReservation(reservation: Reservation) {
        this.reservation = reservation
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
