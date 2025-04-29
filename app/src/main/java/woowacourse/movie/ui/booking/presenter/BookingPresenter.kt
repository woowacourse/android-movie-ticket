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

    private val movie: Movie by lazy { loadMovie() }

    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0
    private val selectedDateTime: LocalDateTime get() = bookingView.getSelectedDateTime()

    fun updateViews() {
        refreshMovieInfo()
        setupDateSpinner()
    }

    override fun increaseHeadcount() {
        _headcount.increase()
        bookingView.updateHeadcountDisplay(_headcount)
    }

    override fun decreaseHeadcount() {
        _headcount.decrease()
        bookingView.updateHeadcountDisplay(_headcount)
    }

    override fun loadMovie(): Movie = bookingView.getMovie() ?: Movie.Companion.DUMMY_MOVIES.first()

    override fun refreshMovieInfo() {
        bookingView.setMovieInfoViews(movie)
    }

    override fun restoreHeadcount(headcount: Headcount) {
        this._headcount = headcount
    }

    override fun refreshHeadcountDisplay() {
        bookingView.updateHeadcountDisplay(_headcount)
    }

    override fun setupDateSpinner() {
        val (startDate, endDate) = movie.releaseDate
        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        bookingView.setDateSpinner(screeningBookingDates, selectedDatePosition)
    }

    override fun setupTimeSpinner() {
        val selectedDate = bookingView.getSelectedDate()
        val screeningTimes =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        bookingView.setTimeSpinner(screeningTimes, selectedTimePosition)
    }

    override fun setSelectedDatePosition(position: Int) {
        selectedDatePosition = position
        setupDateSpinner()
    }

    override fun setSelectedTimePosition(position: Int) {
        selectedTimePosition = position
        setupTimeSpinner()
    }

    override fun completeBooking() {
        bookingView.startBookingSeatActivity(movie.title, selectedDateTime, headcount)
    }
}
