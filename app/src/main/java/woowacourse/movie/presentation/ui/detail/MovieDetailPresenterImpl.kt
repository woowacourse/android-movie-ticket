package woowacourse.movie.presentation.ui.detail

import android.os.Bundle
import woowacourse.movie.domain.model.MovieTicket.Companion.MIN_RESERVATION_COUNT
import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.presentation.dto.ReservationData

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    private val repository: MovieTicketRepository,
    title: String,
    screeningDate: String,
    reservationCount: Int = 1,
) : MovieDetailContract.Presenter {
    init {
        repository.setMovieTicket(title, screeningDate, reservationCount)
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
        repository.updateMovieTicket(movieTicket)
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun plusReservationCount() {
        val movieTicket = repository.getMovieTicket()
        movieTicket.plusCount()
        repository.updateMovieTicket(movieTicket)
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun updateReservationCountDisplay() {
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

    override fun saveState(outState: Bundle) {
        val movieTicket = repository.getMovieTicket()
        outState.putString(EXTRA_TITLE, movieTicket.movieTitle)
        outState.putString(EXTRA_SCREENING_DATE, movieTicket.screeningDate)
        outState.putInt(EXTRA_RESERVATION_COUNT, movieTicket.reservationCount)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        val title = savedInstanceState.getString(EXTRA_TITLE) ?: ""
        val screeningDate = savedInstanceState.getString(EXTRA_SCREENING_DATE) ?: ""
        val reservationCount =
            savedInstanceState.getInt(EXTRA_RESERVATION_COUNT, MIN_RESERVATION_COUNT)
        repository.setMovieTicket(title, screeningDate, reservationCount)
    }

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_SCREENING_DATE = "screeningDate"
        const val EXTRA_RESERVATION_COUNT = "reservationCount"
    }
}
