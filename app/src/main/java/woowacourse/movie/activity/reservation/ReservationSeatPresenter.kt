package woowacourse.movie.activity.reservation

import woowacourse.movie.dto.SeatDto
import woowacourse.movie.global.ServiceLocator

class ReservationSeatPresenter(val view: ReservationSeatContract.View) : ReservationSeatContract.Presenter {
    override fun initSeatTable() {
        val seats = ServiceLocator.seats
        view.initSeatTable(seats.map { SeatDto.fromSeat(it) })
    }
}
