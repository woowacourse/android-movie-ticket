package woowacourse.movie.presentation.ticketingResult

import woowacourse.movie.model.Seat

interface TicketingResultContract {
    interface View {
        fun displayTicketInfo(
            movieTitle: String,
            movieDateTime: String,
            ticketCount: Int,
            selectedSeats: List<Seat>,
            totalPrice: Int,
        )

        fun showToastMessage(message: String?)
    }

    interface Presenter {
        fun loadTicketInfo()
    }
}
