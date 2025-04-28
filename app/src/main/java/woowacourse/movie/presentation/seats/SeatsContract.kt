package woowacourse.movie.presentation.seats

import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.domain.model.seat.Seat

interface SeatsContract {
    interface View {
        fun initSeats()
        fun showMovieTitle(title: String)
        fun showConfirmDialog()
        fun showToast(message: String)
        fun updateAmount(amount: Int)
        fun updateSelectedSeats(seats: List<Seat>)
        fun updateConfirmButtonEnabled(canConfirm: Boolean)
        fun navigateToSummary(ticket: MovieTicket)
    }

    interface Presenter {
        fun onViewCreated()
        fun getSeat(x: Int, y: Int): Seat
        fun getSelectedSeats(): List<Seat>
        fun isSelectedSeat(seat: Seat): Boolean
        fun onSeatClicked(seat: Seat)
        fun onConfirmClicked()
        fun onConfigurationChanged(seats: List<Seat>)
    }
}