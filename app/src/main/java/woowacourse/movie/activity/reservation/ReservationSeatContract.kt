package woowacourse.movie.activity.reservation

import woowacourse.movie.dto.SeatDto

interface ReservationSeatContract {
    interface View {
        fun initSeatTable(seats: List<SeatDto>)

        fun setButtonState(state: Boolean)
    }

    interface Presenter {
        fun initSeatTable()

        fun setButtonState(totalPrice: Int)
    }
}
