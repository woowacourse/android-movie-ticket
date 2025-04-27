package woowacourse.movie.presenter.seat

import woowacourse.movie.view.seat.model.coord.Coordination

interface SeatContract {
    interface View {
        fun onClickSeat(
            position: Coordination,
            peopleCount: Int,
        )

        fun showSeat(seat: List<Coordination>)

        fun showToast(peopleCount: Int)

        fun showPrice(price: Int)

        fun setConfirmButtonEnabled(clickable: Boolean)

        fun moveToBookingComplete(
            seats: String,
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
