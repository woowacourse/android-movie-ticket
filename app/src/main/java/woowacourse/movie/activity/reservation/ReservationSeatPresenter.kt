package woowacourse.movie.activity.reservation

import android.widget.TextView
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.global.ServiceLocator

class ReservationSeatPresenter(val view: ReservationSeatContract.View) : ReservationSeatContract.Presenter {
    override fun initSeatTable() {
        val seats = ServiceLocator.seats
        view.initSeatTable(seats.map { SeatDto.fromSeat(it) })
    }

    override fun setButtonState(totalPrice: Int) {
        if (totalPrice > 0) {
            view.setButtonState(true)
        } else {
            view.setButtonState(false)
        }
    }

    override fun setWhenSeatDisSelected(
        view: TextView,
        seat: SeatDto,
    ) {
        this.view.setWhenSeatDisSelected(view, seat)
    }

    override fun setWhenSeatSelected(
        view: TextView,
        seat: SeatDto,
    ) {
        this.view.setWhenSeatSelected(view, seat)
    }
}
