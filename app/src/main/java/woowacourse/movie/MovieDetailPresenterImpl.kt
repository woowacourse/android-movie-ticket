package woowacourse.movie

import woowacourse.movie.model.MovieTicket

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
}
