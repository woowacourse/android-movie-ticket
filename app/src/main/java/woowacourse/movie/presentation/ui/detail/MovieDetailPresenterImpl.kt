package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    private val movieRepository: MovieRepository,
    private val movieTicketRepository: MovieTicketRepository,
    movieId: Int,
) : MovieDetailContract.Presenter {
    private var movieTicket: MovieTicket? = null

    init {
        if (movieId == INVALID_MOVIE_ID) {
            view.showMessage(INVALID_MOVIE_ID_MESSAGE)
        } else {
            loadMovieDetailsAndSetupTicket(movieId)
        }
    }

    override fun loadMovieDetailsAndSetupTicket(movieId: Int) {
        runCatching {
            movieRepository.getMovie(movieId)
        }.onSuccess { movie ->
            val movieUiModel = MovieUiModel.fromMovie(movie)
            view.showMovieDetail(movieUiModel)
            movieTicket = movieTicketRepository.createMovieTicket(movie.title, movie.screeningDate)
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message))
        }
    }

    private fun modifyReservationCount(modifier: (MovieTicket) -> Unit) {
        movieTicket?.let {
            modifier(it)
            movieTicketRepository.updateReservationCount(it.id, it.reservationCount)
            view.showReservationCount(it.reservationCount)
        } ?: view.showMessage(ERROR_EMPTY_MESSAGE)
    }

    override fun minusReservationCount() {
        modifyReservationCount { it.minusCount() }
    }

    override fun plusReservationCount() {
        modifyReservationCount { it.plusCount() }
    }

    override fun updateReservationCount(reservationCount: Int) {
        modifyReservationCount { it.updateCount(reservationCount) }
    }

    override fun reservationCount(): Int = movieTicket?.reservationCount ?: MIN_RESERVATION_COUNT

    override fun requestReservationResult() {
        movieTicket?.let {
            view.moveToReservationPage(it.id)
        } ?: view.showMessage(ERROR_EMPTY_MESSAGE)
    }

    companion object {
        const val INVALID_MOVIE_ID = -1
        const val MIN_RESERVATION_COUNT = 1
        const val INVALID_MOVIE_ID_MESSAGE = "유효하지 않은 영화 ID입니다."
        const val ERROR_MESSAGE = "영화 정보를 로드하는 과정에서 오류가 발생했습니다: %s"
        const val ERROR_EMPTY_MESSAGE = "예매 정보가 없습니다."
    }
}
