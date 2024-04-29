package woowacourse.movie.presentation.presenter

import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.data.repository.ScreeningMovieInfoRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.reservation.ReservationCount
import woowacourse.movie.domain.model.reservation.ScreeningMovieInfo
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ScreeningMovieInfoRepository
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.uimodel.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailPresenterImpl(
    val movieId: Int,
    private val movieRepository: MovieRepository = MovieRepositoryImpl,
) : MovieDetailContract.Presenter {
    private var view: MovieDetailContract.View? = null
    private val movie: Movie = movieRepository.findMovieById(movieId)
    private val screeningMovieInfo: ScreeningMovieInfo = setScreeningMovieInfo()
    private val reservationCount: ReservationCount = ReservationCount()
    private val screeningMovieInfoRepository: ScreeningMovieInfoRepository =
        ScreeningMovieInfoRepositoryImpl

    private fun setScreeningMovieInfo(): ScreeningMovieInfo {
        return ScreeningMovieInfo(movie.title, movie.screeningInfo)
    }

    override fun attachView(view: MovieDetailContract.View) {
        this.view = view
        onViewSetUp()
    }

    override fun detachView() {
        this.view = null
    }

    override fun onViewSetUp() {
        view?.showMovieDetail(MovieUiModel(movie))
        loadScreeningDates(movieId)
    }

    override fun loadScreeningDates(movieId: Int) {
        val dates =
            movieRepository.getScreeningDateInfo(movieId).map { date ->
                date.format(DEFAULT_DATE_FORMAT)
            }
        val times = getScreeningTimeSchedule(screeningMovieInfo.dateTime.screeningDate.isWeekend())
        view?.setScreeningDatesAndTimes(dates, times, DEFAULT_DATA_INDEX)
    }

    override fun loadScreeningTimes(isWeekend: Boolean) {
        val times = getScreeningTimeSchedule(isWeekend)
        view?.updateScreeningTimes(times, DEFAULT_DATA_INDEX)
    }

    override fun selectDate(date: String) {
        val screeningDate = LocalDate.parse(date, DEFAULT_DATE_FORMAT)
        screeningMovieInfo.changeDate(
            year = screeningDate.year,
            month = screeningDate.monthValue,
            day = screeningDate.dayOfMonth,
        )
        loadScreeningTimes(screeningMovieInfo.dateTime.screeningDate.isWeekend())
    }

    override fun selectTime(time: String) {
        val screeningTime = LocalTime.parse(time)
        screeningMovieInfo.changeTime(
            hour = screeningTime.hour,
            minute = screeningTime.minute,
        )
    }

    override fun minusReservationCount() {
        reservationCount.minusCount()
        view?.showReservationCount(reservationCount.count)
    }

    override fun plusReservationCount() {
        reservationCount.plusCount()
        view?.showReservationCount(reservationCount.count)
    }

    override fun initReservationCount(count: Int) {
        reservationCount.initCount(count)
        view?.showReservationCount(reservationCount.count)
    }

    override fun onReserveButtonClicked() {
        screeningMovieInfoRepository.saveMovieInfo(screeningMovieInfo)
        view?.moveToSeatSelection(reservationCount.count, screeningMovieInfo.title)
    }

    private fun getScreeningTimeSchedule(isWeekend: Boolean): List<String> {
        val times =
            movieRepository.getScreeningTimeInfo(isWeekend).map { time ->
                time.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT))
            }
        return times
    }

    companion object {
        val DEFAULT_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
        const val DEFAULT_TIME_FORMAT = "HH:mm"
        const val DEFAULT_DATA_INDEX = 0
    }
}
