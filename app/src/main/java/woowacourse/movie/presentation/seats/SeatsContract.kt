package woowacourse.movie.presentation.seats

import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatPosition

interface SeatsContract {
    interface View {
        fun initSeats()

        fun showMovieTitle(title: String)

        fun showConfirmDialog()

        fun showToast(message: String)

        fun updateAmount(amount: Int)

        fun updateSelectedSeat(seatPosition: SeatPosition, isSelected: Boolean)

        fun updateConfirmButtonEnabled(canConfirm: Boolean)

        fun navigateToSummary(ticket: MovieTicket)
    }

    interface Presenter {
        fun loadSeats(movieTicket: MovieTicket)

        fun getSelectedSeats(): List<Seat>

        fun selectSeat(seatPosition: SeatPosition)

        fun publishMovieTicket()

        fun restoreSeats(seats: List<Seat>)
    }
}
