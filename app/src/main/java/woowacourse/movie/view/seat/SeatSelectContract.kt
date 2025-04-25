package woowacourse.movie.view.seat

import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.model.TicketUiModel

interface SeatSelectContract {
    interface View {
        fun showMovieInfo(movie: MovieUiModel)

        fun showTotalPrice(price: Int)

        fun showConfirmAlertDialog()

        fun showSelectToast()

        fun updateSeatSelection(
            seat: Seat,
            isSelected: Boolean,
        )

        fun updateConfirmButton(isEnabled: Boolean)

        fun navigateToCompleteScreen(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadSeatSelectScreen()

        fun onClickSeat(seat: Seat)

        fun onClickConfirmButton()

        fun completeReservation()
    }
}
