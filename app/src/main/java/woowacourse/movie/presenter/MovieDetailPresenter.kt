package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.repository.PseudoReservationRepository
import woowacourse.movie.repository.PseudoMovieRepository
import woowacourse.movie.repository.ReservationRepository
import woowacourse.movie.repository.MovieRepository

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    private val movieRepository: MovieRepository = PseudoMovieRepository(),
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
) : MovieDetailContract.Presenter {
    override fun loadMovie(movieId: Int) {
        val screening = movieRepository.getMovie(movieId)
        view.displayMovie(screening)
    }

    override fun plusTicketNum(ticketNum: Int) {
        view.displayTicketNum(ticketNum + 1)
    }

    override fun minusTicketNum(ticketNum: Int) {
        if (ticketNum > 0) view.displayTicketNum(ticketNum - 1)
    }

    override fun purchase(
        movieId: Int,
        ticketNum: Int,
    ) {
        val movie = movieRepository.getMovie(movieId)
        val reservation = Reservation(movie, ticketNum)
        reservationRepository.putReservation(reservation)
        // TODO: if it goes fail, view have to notify that something went wrong
        // e.g. view.notifyException()
        view.navigateToPurchaseConfirmation()
    }
}
