package woowacourse.movie.presenter.seat

import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

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
            view.showSeat(seatToCoordination())
            view.showPrice(seats.bookingPrice())
            updateConfirmButtonState(limit)
        } else {
            view.showToast(limit)
        }
    }

    override fun onConfirmClicked(limit: Int) {
        if (seats.isNotSelectDone(limit)) {
            view.showToast(limit)
            return
        }
        view.moveToBookingComplete(seatToLabel(), seats.bookingPrice())
    }

    private fun updateConfirmButtonState(peopleCount: Int) {
        val isEnabled = seats.item.size == peopleCount
        view.setConfirmButtonEnabled(isEnabled)
    }

    private fun seatToCoordination(): List<Coordination> {
        return seats
            .item
            .map {
                Coordination(Column(it.x), Row(it.y))
            }
    }

    private fun seatToLabel(): String {
        return seats
            .item
            .joinToString {
                val rowLetter = ('A' + it.x - 1)
                val columnNumber = it.y
                "$rowLetter$columnNumber"
            }
    }
}
