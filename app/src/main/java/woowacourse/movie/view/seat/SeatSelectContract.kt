package woowacourse.movie.view.seat

import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.seat.model.SeatUiModel

interface SeatSelectContract {
    interface View {
        fun showMovieInfo(movie: MovieUiModel)

        fun showTotalPrice(price: Int)

        fun showConfirmAlertDialog()

        fun showSelectToast()

        fun updateSeatSelection(
            seat: SeatUiModel,
            isSelected: Boolean,
        )

        fun updateConfirmButton(isEnabled: Boolean)

        fun navigateToCompleteScreen(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadSeatSelectScreen()

        fun onClickSeat(seat: SeatUiModel)

        fun onClickConfirmButton()

        fun completeReservation()
    }
}
