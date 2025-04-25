package woowacourse.movie.seat

import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation

interface SeatContract {
    interface View

    interface Presenter {
        fun getPoint(
            row: Int,
            col: Int,
        ): Point

        fun initReservation(reservation: Reservation)

        fun isOccupied(point: Point): Boolean

        fun cancelSelection(point: Point)

        fun selectSeat(point: Point)
    }
}
