package woowacourse.movie.activity.reservation

import woowacourse.movie.dto.SeatDto

interface ReservationSeatContract {
    interface View {
        fun initSeatTable(seats: List<SeatDto>)
    }

    interface Presenter {
        fun initSeatTable()
    }
}
