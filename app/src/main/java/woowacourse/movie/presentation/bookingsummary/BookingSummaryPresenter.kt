package woowacourse.movie.presentation.bookingsummary

import woowacourse.movie.domain.model.movie.MovieTicket

class BookingSummaryPresenter(
    private val view: BookingSummaryContract.View,
    private val ticket: MovieTicket,
) : BookingSummaryContract.Presenter {
    override fun onViewCreated() {
        view.showTicket(ticket)
    }
}
