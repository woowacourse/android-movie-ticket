package woowacourse.movie.seat

import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation

class SeatPresenter(
    private val view: SeatContract.View,
) : SeatContract.Presenter {
    lateinit var reservation: Reservation

    override fun initReservation(reservation: Reservation) {
        this.reservation = reservation
    }

    override fun getPoint(
        row: Int,
        col: Int,
    ): Point {
        return Point(row, col)
    }

    override fun initView() {
        view.showMovieInfo(reservation.movie)
        view.initSeat()
        view.initSelectButtonClick()
        view.updateTotalPrice(reservation.points.totalPrice())
    }

    override fun isOccupied(point: Point): Boolean {
        return reservation.points.has(point)
    }

    override fun cancelSelection(point: Point) {
        reservation.points - point
        view.updateTotalPrice(reservation.points.totalPrice())
    }

    override fun selectSeat(point: Point) {
        reservation.points + point
        view.updateTotalPrice(reservation.points.totalPrice())
    }

    fun canClickButton(): Boolean {
        return reservation.points.points.isNotEmpty()
    }
}
