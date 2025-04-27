package woowacourse.movie.presenter.booking

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.result.BookingResult
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.sample.SampleMovies
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_SPINNER_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER
import woowacourse.movie.util.DateTimeUtil.toLocalDate
import woowacourse.movie.util.DateTimeUtil.toLocalTime
import woowacourse.movie.util.mapper.BookingResultModelMapper
import woowacourse.movie.util.mapper.MovieModelMapper
import java.time.LocalDate
import java.time.LocalTime

class BookingPresenter(
    val view: BookingContract.View,
    val movieUiModel: MovieUiModel?,
) : BookingContract.Presenter {
    private val movies = SampleMovies()
    private val bookingMovie: Movie by lazy { MovieModelMapper.toDomain(movieUiModel!!) }
    private val booking: Booking by lazy { Booking(bookingMovie) }
    private var bookingResult: BookingResult = initBookingResult(booking)
    private val bookableDates: List<LocalDate> = booking.screeningPeriods()
    private val bookableTimes: List<LocalTime> get() = booking.screeningTimes(bookingResult.selectedDate)

    init {
        if (movieUiModel == null) {
            view.showErrorMessage(ERROR_NOT_EXIST_MOVIE)
        } else {
            loadMovie(movieUiModel)
        }
    }

    override fun loadMovie(movieUiModel: MovieUiModel) {
        if (isExist(movieUiModel)) {
            view.showMovieInfo(movieUiModel)
        } else {
            view.showErrorMessage(ERROR_NOT_EXIST_MOVIE)
        }
    }

    override fun loadScreeningDateTimes() {
        adjustScreeningDateTime()

        val screeningDates = DateTimeUtil.toSpinnerDates(bookableDates)
        view.setScreeningDateSpinner(screeningDates)
        val screeningTimes = DateTimeUtil.toSpinnerTimes(bookableTimes)
        view.setScreeningTimeSpinner(screeningTimes)
    }

    override fun updateScreeningDate(date: String) {
        val screeningDate: LocalDate = date.toLocalDate(MOVIE_SPINNER_DATE_DELIMITER)
        bookingResult = bookingResult.updateDate(screeningDate)
        view.showScreeningDate(bookableDates.indexOf(screeningDate))
        updateScreeningTimes()
    }

    override fun updateScreeningTime(time: String) {
        val screeningTime: LocalTime = time.toLocalTime(MOVIE_TIME_DELIMITER)
        bookingResult = bookingResult.updateTime(screeningTime)
        view.showScreeningTime(bookableTimes.indexOf(screeningTime))
    }

    override fun loadHeadCount() {
        val bookingResultUiModel = BookingResultModelMapper.toUi(bookingResult)
        view.showHeadCount(bookingResultUiModel.headCount)
    }

    override fun increaseHeadCount() {
        bookingResult = bookingResult.plusHeadCount()
        loadHeadCount()
    }

    override fun decreaseHeadCount() {
        if (bookingResult.isHeadCountValid()) bookingResult = bookingResult.minusHeadCount()
        loadHeadCount()
    }

    override fun reserve() {
        if (bookingResult.isHeadCountValid()) {
            val bookingResultUiModel = BookingResultModelMapper.toUi(bookingResult)
            view.showConfirmDialog(bookingResultUiModel)
        }
    }

    private fun initBookingResult(booking: Booking): BookingResult {
        val nearestDate = booking.screeningPeriods()[0]
        val availBookingTimes = booking.screeningTimes(nearestDate)
        val nearestTime = availBookingTimes[0]
        return BookingResult(
            title = bookingMovie.title,
            headCount = 0,
            selectedDate = nearestDate,
            selectedTime = nearestTime,
        )
    }

    private fun isExist(movieUiModel: MovieUiModel): Boolean {
        return movies.movieUiModels.any { movie -> movie.id == movieUiModel.id }
    }

    private fun adjustScreeningDateTime() {
        val nextDay = bookingResult.selectedDate.plusDays(1)
        if (bookableTimes.isEmpty() && !bookingMovie.isScreeningEnd(nextDay)) {
            bookingResult = bookingResult.updateDate(nextDay)
        }
    }

    private fun updateScreeningTimes() {
        val screeningTimes = DateTimeUtil.toSpinnerTimes(bookableTimes)
        bookingResult = bookingResult.updateTime(bookableTimes.first())
        view.setScreeningTimeAdapter(screeningTimes)
    }

    companion object {
        private const val ERROR_NOT_EXIST_MOVIE = "영화 정보가 존재하지 않습니다."
    }
}
