package woowacourse.movie.presenter.seat

import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.view.seat.model.coord.Coordination

class SeatPresenter(
    private val view: SeatContract.View,
    private val seats: Seats,
) : SeatContract.Presenter {
    override fun changeSeat(
        position: Coordination,
        limit: Int,
    ) {
        val newSeat = Seat(x = position.x.value, y = position.y.value)
        val changed = seats.toggleSeat(newSeat, limit)

        if (changed) {
            view.showSeat(seats.item)
            view.showPrice(seats.bookingPrice())
            updateConfirmButtonState(limit)
        } else {
            view.showToast(limit)
        }
    }

    override fun attemptConfirmBooking(limit: Int) {
        if (seats.isNotSelectDone(limit)) {
            view.showToast(limit)
            return
        }
        view.moveToBookingComplete(seats.item, seats.bookingPrice())
    }

    private fun updateConfirmButtonState(peopleCount: Int) {
        val isEnabled = seats.item.size == peopleCount
        view.setConfirmButtonEnabled(isEnabled)
    }
}
