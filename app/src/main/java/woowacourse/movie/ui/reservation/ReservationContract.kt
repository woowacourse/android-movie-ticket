package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movie.Movie

interface ReservationContract {
    interface View {
        fun showMovie(existingMovie: Movie)

        fun updateTicketCount(count: Int)

        fun showTicket(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovie()

        fun onClickedPlusButton(count: Int)

        fun onClickedSubButton(count: Int)

        fun onClickedReservation(
            existingMovie: Movie,
            count: Int,
        )
    }
}
