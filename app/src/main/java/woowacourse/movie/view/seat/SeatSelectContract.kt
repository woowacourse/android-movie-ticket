package woowacourse.movie.view.seat

import woowacourse.movie.domain.Position
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.model.TicketUiModel

interface SeatSelectContract {
    interface View {
        fun showMovieInfo(movie: MovieUiModel)

        fun showTotalPrice(price: Int)

        fun updateSeatSelection(
            position: Position,
            isSelected: Boolean,
        )

        fun updateConfirmButton(isEnabled: Boolean)

        fun navigateToCompleteScreen(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadSeatSelectScreen()

        fun onClickSeat(position: Position)

        fun completeReservation()
    }
}
