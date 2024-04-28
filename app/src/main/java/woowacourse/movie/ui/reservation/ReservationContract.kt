package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movie.Movie

interface ReservationContract {
    interface View {
        fun showMovie(existingMovie: Movie)

        fun updateCount(count: Int)

        // needed - naming signature change
        fun showTicket(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovie()

        fun onClickedPlusButton(count: Int)
        // fun addCount()

        fun onClickedSubButton(count: Int)

        // subCount()
        fun onClickedReservation(
            existingMovie: Movie,
            count: Int,
        )
        // completeReservation()
    }
}
