package woowacourse.movie.presenter

import woowacourse.movie.MovieBooking
import woowacourse.movie.R
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTimes
import java.time.LocalDate
import java.time.LocalTime

class MovieBookingPresenter(
    private val view: MovieBooking.View
) : MovieBooking.Presenter {
    private lateinit var movie: Movie
    private var count: Int = 1
    private var bookedDate: LocalDate = LocalDate.now()
    private var bookedDates: List<LocalDate> = emptyList()
    private var bookedTime: LocalTime = LocalTime.now()

    override fun loadMovie(movie: Movie) {
        this.movie = movie
        bookedDates = movie.screeningPeriod.betweenDates()
        bookedDate = bookedDates.first()
        view.showMovieInfo()
        view.updateMemberCount(this.count)
        view.showBookingDate(bookedDates)
        view.showBookingTimes(RunningTimes(bookedDate).runningTimes())
    }

    override fun increaseCount() {
        count++
        view.updateMemberCount(count)
    }

    override fun decreaseCount() {
        if (count <= 1) {
            view.showError(R.string.error_people_over_one)
            return
        }
        count--
        view.updateMemberCount(count)
    }

    override fun selectDate(date: LocalDate) {
        bookedDate = date
        view.showBookingTimes(RunningTimes(date).runningTimes())
    }

    override fun selectTime(time: LocalTime) {
        bookedTime = time
    }

    override fun confirmBooking() {
        val bookingStatus = BookingStatus(movie, count, bookedDate, bookedTime)
        view.showConfirmDialog(bookingStatus)
    }
}
