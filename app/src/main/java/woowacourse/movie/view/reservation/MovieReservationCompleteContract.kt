package woowacourse.movie.view.reservation

import woowacourse.movie.domain.Ticket

interface MovieReservationCompleteContract {
    interface View {
        fun showMovieInfo(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovieReservationCompleteScreen()
    }
}
