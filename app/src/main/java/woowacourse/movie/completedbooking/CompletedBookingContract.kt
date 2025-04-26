package woowacourse.movie.completedbooking

import woowacourse.movie.domain.Ticket

interface CompletedBookingContract {
    interface Presenter {
        fun set(ticket: Ticket)
    }
    interface View {
        fun showCancelDeadLine(deadline: Int)
        fun showMovieTitle(title: String)
        fun showMovieDateTime(dateTime: String)
        fun showPersonnel(personnel: Int)
        fun showTicketTotalPrice(price: String)
    }
}