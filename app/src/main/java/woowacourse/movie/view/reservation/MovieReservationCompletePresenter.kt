package woowacourse.movie.view.reservation

import woowacourse.movie.domain.Ticket

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val ticket: Ticket,
) : MovieReservationCompleteContract.Presenter {
    override fun loadMovieReservationCompleteScreen() {
        view.showMovieInfo(ticket)
    }
}
