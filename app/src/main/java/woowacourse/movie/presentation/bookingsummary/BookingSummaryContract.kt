package woowacourse.movie.presentation.bookingsummary

import woowacourse.movie.domain.model.movie.MovieTicket

interface BookingSummaryContract {
    interface View {
        fun showTicket(ticket: MovieTicket)
    }

    interface Presenter {
        fun onViewCreated()
    }
}
