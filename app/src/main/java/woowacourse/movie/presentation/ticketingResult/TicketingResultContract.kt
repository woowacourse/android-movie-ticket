package woowacourse.movie.presentation.ticketingResult

import woowacourse.movie.model.Seat

interface TicketingResultContract {
    interface View {
        fun assignInitialView(
            movieTitle: String,
            movieDateTime: String,
            ticketCount: Int,
            selectedSeats: List<Seat>,
            totalPrice: Int,
        )

        fun showErrorMessage(message: String?)
    }

    interface Presenter {
        fun assignInitialView()
    }
}
