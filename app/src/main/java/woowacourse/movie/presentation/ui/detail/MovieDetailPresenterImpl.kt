package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.presentation.dto.ReservationData

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    private val repository: MovieTicketRepository,
    title: String,
    screeningDate: String,
) : MovieDetailContract.Presenter {
    init {
        repository.setMovieTicket(title, screeningDate)
    }

    override fun loadMovieDetails(
        posterImageId: Int,
        title: String,
        screeningDate: String,
        runningTime: Int,
        summary: String,
    ) {
        view.showMovieDetail(posterImageId, title, screeningDate, runningTime, summary)
    }

    override fun minusReservationCount() {
        val movieTicket = repository.getMovieTicket()
        movieTicket.minusCount()
        repository.updateReservationCount(movieTicket)
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun plusReservationCount() {
        val movieTicket = repository.getMovieTicket()
        movieTicket.plusCount()
        repository.updateReservationCount(movieTicket)
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun updateReservationCount(reservationCount: Int) {
        val movieTicket = repository.getMovieTicket()
        movieTicket.updateCount(reservationCount)
        repository.updateReservationCount(movieTicket)
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun reservationCountDisplay() {
        val movieTicket = repository.getMovieTicket()
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun requestReservationResult() {
        val movieTicket = repository.getMovieTicket()
        val reservationData =
            ReservationData(
                movieTitle = movieTicket.movieTitle,
                screeningDate = movieTicket.screeningDate,
                reservationCount = movieTicket.reservationCount,
                totalPrice = movieTicket.totalPrice(),
            )
        view.moveToReservationPage(reservationData)
    }
}
