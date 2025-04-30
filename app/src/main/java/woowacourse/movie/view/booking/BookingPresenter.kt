package woowacourse.movie.view.booking

import woowacourse.movie.data.MovieStore
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
    private val movies: MovieStore,
    private val selectedMovie: Movie,
    private var count: PeopleCount,
) : BookingContract.Presenter {
    override fun loadMovieDetail() {
        view.showMovieDetail(selectedMovie)
    }

    override fun loadPeopleCount() {
        view.showPeopleCount(count.value)
    }

    override fun loadScreeningDate(
        startDate: LocalDate,
        endDate: LocalDate,
        now: LocalDateTime,
    ) {
        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(
                startDate,
                endDate,
            )
                .bookingDates(now.toLocalDate())

        view.showScreeningDate(screeningBookingDates)
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
}
