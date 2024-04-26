package woowacourse.movie.presentation.ticketingResult

import woowacourse.movie.model.Ticket

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
    private val movieTicket: Ticket?,
) : TicketingResultContract.Presenter {
    override fun assignInitialView() {
        movieTicket?.let { ticket ->
            ticketingResultView.assignInitialView(
                ticket.movieTitle,
                ticket.screeningDateTime,
                ticket.totalCount,
                ticket.selectedSeats,
                ticket.totalPrice,
            )
        }
    }
}
