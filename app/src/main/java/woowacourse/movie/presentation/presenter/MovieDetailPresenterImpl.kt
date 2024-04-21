package woowacourse.movie.presentation.presenter

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presentation.contract.MovieDetailContract

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    title: String,
    screeningDate: String,
) : MovieDetailContract.Presenter {
    override val movieTicket: MovieTicket = MovieTicket(title, screeningDate)

    override fun minusReservationCount() {
        movieTicket.minusCount()
        view.showReservationCount(movieTicket.count)
    }

    override fun plusReservationCount() {
        movieTicket.plusCount()
        view.showReservationCount(movieTicket.count)
    }

    override fun initReservationCount(count: Int) {
        movieTicket.initCount(count)
        view.showReservationCount(movieTicket.count)
    }
}
