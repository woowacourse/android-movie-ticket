package woowacourse.movie.seat

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat

interface SeatContract {
    interface View {
        fun showMovieInfo(movie: Movie)

        fun showTotalPrice(price: Int)
    }

    interface Presenter {
        fun initReservation(reservation: Reservation)

        fun updateReservationInfo()

        fun getSeat(
            row: Int,
            col: Int,
        ): Seat

        fun isOccupied(seat: Seat): Boolean

        fun cancelSelection(seat: Seat)

        fun selectSeat(seat: Seat)

        fun canReserve(): Boolean
    }
}
