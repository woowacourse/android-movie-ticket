package woowacourse.movie.seat

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation

interface SeatContract {
    interface View {
        fun initSeat()

        fun showMovieInfo(movie: Movie)

        fun updateTotalPrice(price: Int)

        fun initSelectButtonClick()
    }

    interface Presenter {
        fun initReservation(reservation: Reservation)

        fun getPoint(
            row: Int,
            col: Int,
        ): Point

        fun initView()

        fun isOccupied(point: Point): Boolean

        fun cancelSelection(point: Point)

        fun selectSeat(point: Point)

        fun canReserve(): Boolean
    }
}
