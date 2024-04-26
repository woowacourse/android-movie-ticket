package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.model.ScreeningSchedule
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.uimodel.ReservationCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    private val movieRepository: MovieRepository,
    private val movieTicketRepository: MovieTicketRepository,
    movieId: Int,
) : MovieDetailContract.Presenter {
    private val reservationCount = ReservationCount()
    private val screeningSchedule = ScreeningSchedule()
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
            movieUiModel = MovieUiModel.fromMovie(movie)
            view.showMovieDetail(movieUiModel)
            loadMovieScheduleDates(movieUiModel.screeningDates)
            updateMovieScheduleDate(movieUiModel.screeningDates.min())
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message))
        }
    }

    override fun loadMovieScheduleDates(dates: List<LocalDate>) {
        view.updateSpinnerAdapter(dates)
    }

    override fun updateMovieScheduleDate(date: LocalDate) {
        val times = screeningSchedule.times(date)
        screeningSchedule.updateDate(date)
        view.updateTimeSpinnerAdapter(times)
    }

    override fun updateMovieScheduleTime(time: LocalTime) {
        screeningSchedule.updateTime(time)
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

    private fun createMovieTicket(movieSchedule: LocalDateTime): MovieTicket =
        movieTicketRepository.createMovieTicket(
            movieUiModel.title,
            movieSchedule,
            reservationCount.count,
        )

    override fun requestReservationResult() {
        if (screeningSchedule.isValidate()) {
            val dateTime = screeningSchedule.dateTime
            dateTime?.let {
                val movieTicket = createMovieTicket(dateTime)
                view.moveToReservationPage(movieTicket.id)
            } ?: view.showMessage(INVALID_DATE_TIME_MESSAGE)
        } else {
            view.showMessage(INVALID_DATE_TIME_MESSAGE)
        }
    }

    companion object {
        const val INVALID_MOVIE_ID = -1
        const val INVALID_MOVIE_ID_MESSAGE = "유효하지 않은 영화 ID입니다."
        const val ERROR_MESSAGE = "영화 정보를 로드하는 과정에서 오류가 발생했습니다: %s"
        const val INVALID_DATE_TIME_MESSAGE = "올바르지 않은 날짜나 시간입니다."
    }
}
