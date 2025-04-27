package woowacourse.movie.seat

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat

interface SeatContract {
    interface View {
        fun initSeat()

        fun showMovieInfo(movie: Movie)

        fun updateTotalPrice(price: Int)

        fun initSelectButtonClick()
    }

    interface Presenter {
        fun initReservation(reservation: Reservation)

        fun getSeat(
            row: Int,
            col: Int,
        ): Seat

        fun initView()

        fun isOccupied(seat: Seat): Boolean

        fun cancelSelection(seat: Seat)

        fun selectSeat(seat: Seat)

        fun canReserve(): Boolean
    }
}
