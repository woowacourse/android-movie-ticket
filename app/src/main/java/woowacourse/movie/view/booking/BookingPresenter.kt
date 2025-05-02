package woowacourse.movie.view.booking

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.booking.ScreeningTime
import woowacourse.movie.domain.model.movies.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenter(
    private val view: BookingContract.View,
    private val selectedMovie: Movie,
    private var count: PeopleCount,
    private val initialTime: LocalDateTime = LocalDateTime.now(),
) : BookingContract.Presenter {
    init {
        loadPeopleCount()
    }

    override fun loadMovieDetail() {
        view.showMovieDetail(selectedMovie)
        loadScreening()
    }

    override fun loadScreeningTime(
        selectedDate: LocalDate,
        now: LocalDateTime,
    ) {
        val screeningTime = ScreeningTime(now, selectedDate)
        val availableTimes = screeningTime.getAvailableScreeningTimes()

        if (availableTimes.isEmpty()) {
            view.showToast()
            return
        }
        view.showScreeningTime(availableTimes)
    }

    override fun decreasePeopleCount() {
        count = count.decrease()
        view.showPeopleCount(count.value)
    }

    override fun increasePeopleCount(limit: Int) {
        count = count.increase(limit)
        view.showPeopleCount(count.value)
    }

    override fun restorePeopleCount(savedCount: Int) {
        count = PeopleCount(savedCount)
        view.showPeopleCount(count.value)
    }

    override fun loadBooking(
        title: String,
        bookingDate: String,
        bookingTime: String,
        count: String,
    ) {
        val booking =
            Booking(
                title = title,
                bookingDate = LocalDate.parse(bookingDate),
                bookingTime = LocalTime.parse(bookingTime),
                count = PeopleCount(count.toInt()),
            )

        view.moveToBookingComplete(booking)
    }

    private fun loadScreening() {
        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(
                selectedMovie.releaseDate.startDate,
                selectedMovie.releaseDate.endDate,
            )
                .bookingDates(initialTime.toLocalDate())

        view.showScreeningDate(screeningBookingDates)
        loadScreeningTime(screeningBookingDates[0], initialTime)
    }

    private fun loadPeopleCount() {
        view.showPeopleCount(count.value)
    }
}
