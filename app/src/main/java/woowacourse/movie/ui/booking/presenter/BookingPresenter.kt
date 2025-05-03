package woowacourse.movie.ui.booking.presenter

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import woowacourse.movie.ui.booking.contract.BookingContract
import woowacourse.movie.ui.booking.model.BookingState
import java.time.LocalDate
import java.time.LocalDateTime

class BookingPresenter(
    private val bookingView: BookingContract.View,
) : BookingContract.Presenter {
    private var _headcount: Headcount = Headcount()
    val headcount get() = _headcount.deepCopy()

    private lateinit var movie: Movie

    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0
    private lateinit var selectedDateTime: LocalDateTime

    fun updateViews() {
        refreshMovieInfo()
        setupDateSpinner()
        setupTimeSpinner()
    }

    override fun increaseHeadcount() {
        _headcount.increase()
        bookingView.updateHeadcountDisplay(_headcount)
    }

    override fun savedBookingState(): BookingState =
        BookingState(
            movie,
            headcount,
            selectedDatePosition,
            selectedTimePosition,
            selectedDateTime,
        )

    override fun restoreState(bookingState: BookingState) {
        with(bookingState) {
            this@BookingPresenter.movie = movie
            this@BookingPresenter._headcount = headcount
            this@BookingPresenter.selectedDatePosition = selectedDatePosition
            this@BookingPresenter.selectedTimePosition = selectedTimePosition
            this@BookingPresenter.selectedDateTime = selectedDateTime
        }
    }

    override fun restoreHeadcount(headcount: Headcount) {
        this._headcount = headcount
    }

    override fun loadSelectedDatePosition(selectedDatePosition: Int) {
        this.selectedDatePosition = selectedDatePosition
    }

    override fun loadSelectedTimePosition(selectedTimePosition: Int) {
        this.selectedTimePosition = selectedTimePosition
    }

    override fun loadSelectedLocalDateTime(selectedDateTime: LocalDateTime) {
        this.selectedDateTime = selectedDateTime
    }

    override fun decreaseHeadcount() {
        _headcount.decrease()
        bookingView.updateHeadcountDisplay(_headcount)
    }

    override fun refreshMovieInfo() {
        bookingView.setMovieInfoViews(movie)
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
        val screeningTimes =
            ScreeningTime().getAvailableScreeningTimes(
                LocalDateTime.now(),
                selectedDateTime.toLocalDate(),
            )

        bookingView.setTimeSpinner(screeningTimes, selectedTimePosition)
    }

    override fun completeBooking() {
        bookingView.startBookingSeatActivity(movie.title, selectedDateTime, headcount)
    }
}
