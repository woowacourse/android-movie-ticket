package woowacourse.movie.presenter.booking

import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.sample.SampleMovies
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER
import woowacourse.movie.util.DateTimeUtil.toLocalDate
import woowacourse.movie.util.DateTimeUtil.toLocalTime
import woowacourse.movie.util.mapper.BookingResultModelMapper
import woowacourse.movie.util.mapper.MovieModelMapper
import java.time.LocalDate
import java.time.LocalTime

class BookingPresenter(
    val view: BookingContract.View,
) : BookingContract.Presenter {
    private val movies = SampleMovies()
    private lateinit var bookingMovie: Movie
    private lateinit var booking: Booking
    private lateinit var bookingResult: BookingResult
    val bookingResultUiModel: BookingResultUiModel
        get() = BookingResultModelMapper.toUi(bookingResult)
    private lateinit var bookableDates: List<LocalDate>
    private val bookableTimes: List<LocalTime> get() = booking.screeningTimes(bookingResult.selectedDate)

    override fun loadMovie(movieUiModel: MovieUiModel?) {
        movieUiModel?.let {
            validateExistMovieInMovies(movieUiModel)
        } ?: view.showErrorMessage(R.string.error_not_exist_movie)
    }

    override fun loadScreeningDateTimes() {
        adjustScreeningDateTime()

        val screeningDates = DateTimeUtil.toSpinnerDates(bookableDates)
        view.setScreeningDateSpinner(screeningDates)
        val screeningTimes = DateTimeUtil.toSpinnerTimes(bookableTimes)
        view.setScreeningTimeSpinner(screeningTimes)
    }

    override fun updateScreeningDate(
        date: String,
        delimiter: String,
    ) {
        val screeningDate: LocalDate = date.toLocalDate(delimiter)
        bookingResult = bookingResult.updateDate(screeningDate)
        view.showScreeningDate(bookableDates.indexOf(screeningDate))
        view.setScreeningTimeAdapter(DateTimeUtil.toSpinnerTimes(bookableTimes))
        view.showScreeningTime(bookableTimes.indexOf(bookingResult.selectedTime))
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
            view.moveTo(bookingResultUiModel)
        }
    }

    override fun restoreBookingResult(
        count: Int,
        date: String?,
        time: String?,
    ) {
        bookingResult =
            bookingResult.updateDate(date?.toLocalDate(MOVIE_DATE_DELIMITER) ?: LocalDate.now())
        bookingResult =
            bookingResult.updateTime(time?.toLocalTime(MOVIE_TIME_DELIMITER) ?: LocalTime.now())
        bookingResult = bookingResult.updateCount(count)

        val bookingResultUiModel = BookingResultModelMapper.toUi(bookingResult)
        view.showHeadCount(bookingResultUiModel.headCount)
    }

    private fun validateExistMovieInMovies(movieUiModel: MovieUiModel) {
        if (isExist(movieUiModel)) {
            view.showMovieInfo(movieUiModel)
            initBookingInfos(movieUiModel)
        } else {
            view.showErrorMessage(R.string.error_not_exist_movie)
        }
    }

    private fun initBookingInfos(movieUiModel: MovieUiModel) {
        bookingMovie = MovieModelMapper.toDomain(movieUiModel)
        booking = Booking(bookingMovie)
        bookingResult = initBookingResult(booking)
        bookableDates = booking.screeningPeriods()
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
}
