package woowacourse.movie.ui.booking.presenter

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import woowacourse.movie.ui.booking.contract.BookingContract
import java.time.LocalDate
import java.time.LocalDateTime

class BookingPresenter(
    private val bookingView: BookingContract.View,
) : BookingContract.Presenter {
    private var _headcount: Headcount = Headcount()
    val headcount get() = _headcount.deepCopy()

    private val movie: Movie by lazy { restoreMovie() }

    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0
    private val selectedDateTime: LocalDateTime get() = bookingView.getSelectedDateTime()

    fun restoreMovie(): Movie = fetchMovie()

    fun updateViews() {
        updateMovieInfoViews()
        updateDateSpinner()
    }

    override fun increaseHeadcount() {
        _headcount.increase()
        bookingView.setHeadcountTextView(_headcount)
    }

    override fun decreaseHeadcount() {
        _headcount.decrease()
        bookingView.setHeadcountTextView(_headcount)
    }

    override fun fetchMovie(): Movie = bookingView.getMovie() ?: Movie.Companion.DUMMY_MOVIES.first()

    override fun updateMovieInfoViews() {
        bookingView.setMovieInfoViews(movie)
    }

    override fun updateHeadcount(headcount: Headcount) {
        this._headcount = headcount
    }

    override fun updateHeadcountTextView() {
        bookingView.setHeadcountTextView(_headcount)
    }

    override fun updateDateSpinner() {
        val (startDate, endDate) = movie.releaseDate
        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        bookingView.setDateSpinner(screeningBookingDates, selectedDatePosition)
    }

    override fun updateTimeSpinner() {
        val selectedDate = bookingView.getSelectedDate()
        val screeningTimes =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        bookingView.setTimeSpinner(screeningTimes, selectedTimePosition)
    }

    override fun updateSelectedDatePosition(position: Int) {
        selectedDatePosition = position
        updateDateSpinner()
    }

    override fun updateSelectedTimePosition(position: Int) {
        selectedTimePosition = position
        updateTimeSpinner()
    }

    override fun completeBooking() {
        bookingView.moveToBookingSeatActivity(movie.title, selectedDateTime, headcount)
    }
}
