package woowacourse.movie.completedbooking

import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.DateFormatter
import woowacourse.movie.utils.PriceFormatter

class CompletedBookingPresenter(private val view: CompletedBookingContract.View) : CompletedBookingContract.Presenter {
    private lateinit var ticket: Ticket

    override fun set(ticket: Ticket) {
        this.ticket = ticket
        view.showCancelDeadLine(CANCEL_DEADLINE)
        view.showMovieTitle(ticket.title)

        val dateFormatter = DateFormatter()
        val formattedDateTime = dateFormatter.format(ticket.date)
        view.showMovieDateTime(formattedDateTime)

        view.showPersonnel(ticket.personnel)

        val priceFormatter = PriceFormatter()
        val formattedPrice = priceFormatter.format(DEFAULT_PRICE * ticket.personnel)
        view.showTicketTotalPrice(formattedPrice)
    }

    companion object {
        private const val DEFAULT_PRICE = 13_000
        private const val CANCEL_DEADLINE = 15
    }
}
