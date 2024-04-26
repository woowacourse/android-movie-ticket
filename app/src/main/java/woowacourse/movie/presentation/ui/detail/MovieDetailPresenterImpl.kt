package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.uimodel.ReservationCount
import java.time.LocalDate

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    private val movieRepository: MovieRepository,
    private val movieTicketRepository: MovieTicketRepository,
    movieId: Int,
) : MovieDetailContract.Presenter {
    private var movieTicket: MovieTicket? = null
    private val reservationCount = ReservationCount()
    private lateinit var movieUiModel: MovieUiModel

    init {
        if (movieId == INVALID_MOVIE_ID) {
            view.showMessage(INVALID_MOVIE_ID_MESSAGE)
        } else {
            loadMovieDetails(movieId)
        }
    }

    override fun loadMovieDetails(movieId: Int) {
        runCatching {
            movieRepository.getMovie(movieId)
        }.onSuccess { movie ->
            val movieUiModel = MovieUiModel.fromMovie(movie)
            view.showMovieDetail(movieUiModel)
            loadMovieScheduleDates(movieUiModel.screeningDates)
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message))
        }
    }

    override fun loadMovieScheduleDates(dates: List<LocalDate>) {
        view.initSpinnerAdapter(dates)
    }

    override fun minusReservationCount() {
        reservationCount.minusCount()
        view.showReservationCount(reservationCount.count)
    }

    override fun plusReservationCount() {
        reservationCount.plusCount()
        view.showReservationCount(reservationCount.count)
    }

    override fun updateReservationCount(count: Int) {
        reservationCount.updateCount(count)
        view.showReservationCount(reservationCount.count)
    }

    override fun reservationCount(): Int = reservationCount.count

    private fun createMovieTicket(movieSchedule: LocalDate) {
        movieTicket = movieTicketRepository.createMovieTicket(movieUiModel.title, movieSchedule, reservationCount.count)
    }

    override fun requestReservationResult() {
        movieTicket?.let {
            createMovieTicket(LocalDate.now()) // Todo: movieSchedule 변경
            view.moveToReservationPage(it.id)
        } ?: view.showMessage(ERROR_EMPTY_MESSAGE)
    }

    companion object {
        const val INVALID_MOVIE_ID = -1
        const val INVALID_MOVIE_ID_MESSAGE = "유효하지 않은 영화 ID입니다."
        const val ERROR_MESSAGE = "영화 정보를 로드하는 과정에서 오류가 발생했습니다: %s"
        const val ERROR_EMPTY_MESSAGE = "예매 정보가 없습니다."
    }
}
