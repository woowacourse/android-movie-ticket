package woowacourse.movie.view.booking

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.booking.TicketType
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.movies.MovieListContract
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BookingPresenter(
    private val view: BookingContract.View,
    private val movie: MovieListContract.MovieModel,
    private var count: PeopleCount,
) : BookingContract.Presenter {
    override fun loadMovieDetail(index: Int) {
        val movie = movie[index]

        view.showMovieDetail(
            title = movie.title,
            posterResId = movie.posterResource.posterId,
            releaseStartDate = StringFormatter.dotDateFormat(movie.releaseDate.startDate),
            releaseEndDate = StringFormatter.dotDateFormat(movie.releaseDate.endDate),
            runningTime = movie.runningTime,
        )
    }

    override fun loadPeopleCount() {
        view.showPeopleCount(count.value)
    }

    override fun loadScreeningDate(
        startDate: String,
        endDate: String,
        now: LocalDate,
    ) {
        val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")

        val screeningBookingDates: List<String> =
            ScreeningDate(
                LocalDate.parse(startDate, formatter),
                LocalDate.parse(endDate, formatter),
            )
                .bookingDates(LocalDate.now())
                .map { it.toString() }

        view.showScreeningDate(screeningBookingDates)
    }

    override fun decreasePeopleCount() {
        count = count.decrease()
        view.showPeopleCount(count.value)
    }

    override fun increasePeopleCount() {
        count = count.increase()
        view.showPeopleCount(count.value)
    }

    override fun loadBooking(
        title: String,
        bookingDate: String,
        bookingTime: String,
        count: String,
        ticketType: TicketType,
    ) {
        val booking =
            Booking(
                title = title,
                bookingDate = LocalDate.parse(bookingDate),
                bookingTime = LocalTime.parse(bookingTime),
                count = PeopleCount(count.toInt()),
                ticketType = ticketType,
            )
        view.moveToBookingComplete(booking)
    }
}
