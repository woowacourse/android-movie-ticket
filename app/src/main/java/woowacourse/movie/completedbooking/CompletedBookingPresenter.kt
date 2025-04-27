package woowacourse.movie.completedbooking

import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.DateFormatter
import woowacourse.movie.utils.PriceFormatter

class CompletedBookingPresenter(private val view: CompletedBookingContract.View) : CompletedBookingContract.Presenter {
    private lateinit var ticket: Ticket

    override fun set(ticket: Ticket) {
        this.ticket = ticket
        view.showCancelDeadLine(CANCEL_DEADLINE)
        view.showMovieTitle(ticket.reservationInfo.title)

        val dateFormatter = DateFormatter()
        val formattedDateTime = dateFormatter.format(ticket.reservationInfo.date)
        view.showMovieDateTime(formattedDateTime)

        val seats: String = ticket.seat.joinToString()

        view.showPersonnel(ticket.reservationInfo.personnel, seats)

        val priceFormatter = PriceFormatter()
        val formattedPrice = priceFormatter.format(ticket.totalPrice)
        view.showTicketTotalPrice(formattedPrice)
    }

    companion object {
        private const val CANCEL_DEADLINE = 15
    }
}
