package woowacourse.movie.presenter.seat

import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.view.seat.model.coord.Coordination

interface SeatContract {
    interface View {
        fun onClickSeat(
            position: Coordination,
            peopleCount: Int,
        )

        fun showSeat(seat: Set<Seat>)

        fun showToast(peopleCount: Int)

        fun showPrice(price: Int)

        fun setConfirmButtonEnabled(clickable: Boolean)

        fun moveToBookingComplete(
            seats: Set<Seat>,
            price: Int,
        )
    }

    interface Presenter {
        fun changeSeat(
            position: Coordination,
            limit: Int,
        )

        fun onConfirmClicked(limit: Int)
    }
}
