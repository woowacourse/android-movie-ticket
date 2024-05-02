package woowacourse.movie.feature.complete

import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.TicketRepository

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieRepository: MovieRepository,
    private val ticketRepository: TicketRepository,
) : MovieReservationCompleteContract.Presenter {
    override fun loadTicketData(ticketId: Long) {
        val ticket = ticketRepository.find(ticketId)
        view.initializeTicket(ticket)
    }

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        view.initializeReservationCompleteView(movie)
    }
}
