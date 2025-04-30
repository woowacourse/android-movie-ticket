package woowacourse.movie.seat

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat

class SeatPresenter(
    private val view: SeatContract.View,
) : SeatContract.Presenter {
    lateinit var reservation: Reservation

    override fun initReservation(reservation: Reservation) {
        this.reservation = reservation
    }

    override fun updateReservationInfo() {
        view.showMovieInfo(reservation.movie)
        view.showTotalPrice(reservation.seats.totalPrice())
    }

    override fun getSeat(
        row: Int,
        col: Int,
    ): Seat {
        return Seat(row, col)
    }

    override fun isOccupied(seat: Seat): Boolean {
        return reservation.seats.has(seat)
    }

    override fun cancelSelection(seat: Seat) {
        reservation.seats - seat
        view.showTotalPrice(reservation.seats.totalPrice())
    }

    override fun selectSeat(seat: Seat) {
        reservation.seats + seat
        view.showTotalPrice(reservation.seats.totalPrice())
    }

    override fun canReserve(): Boolean {
        return reservation.seats.seats.size == reservation.count
    }
}
