package woowacourse.movie.seat

import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation

class SeatPresenter : SeatContract.Presenter {
    lateinit var reservation: Reservation

    override fun getPoint(
        row: Int,
        col: Int,
    ): Point {
        return Point(row, col)
    }

    override fun initReservation(reservation: Reservation) {
        this.reservation = reservation
    }

    override fun isOccupied(point: Point): Boolean {
        return reservation.points.has(point)
    }

    override fun cancelSelection(point: Point) {
        reservation.points - point
    }

    override fun selectSeat(point: Point) {
        reservation.points + point
    }
}
