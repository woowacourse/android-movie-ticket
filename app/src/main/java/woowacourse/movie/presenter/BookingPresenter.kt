package woowacourse.movie.presenter

import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.schedule.MovieScheduler
import woowacourse.movie.domain.service.MovieTicketService
import woowacourse.movie.ui.view.booking.BookingContract
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenter(
    private val view: BookingContract.View,
    private val movieId: Int,
    private var headCount: HeadCount = HeadCount(),
    private val movieTicketService: MovieTicketService = MovieTicketService(),
) : BookingContract.Presenter {
    private val movie = getMovie()
    private val movieScheduler = MovieScheduler(movie.startScreeningDate, movie.endScreeningDate)

    override fun getHeadCount(): Int = headCount.getCount()

    override fun loadInitialHeadCount() {
        view.updateHeadCount(getHeadCount())
    }

    override fun increaseHeadCount() {
        headCount.increase()
        view.updateHeadCount(getHeadCount())
    }

    override fun decreaseHeadCount() {
        headCount.decrease()
        view.updateHeadCount(getHeadCount())
    }

    override fun restoreHeadCount(restoredCount: Int) {
        headCount = HeadCount(restoredCount)
    }

    override fun getMovie(): Movie = MovieRepository().getMovieById(movieId)

    override fun onConfirm(screeningDateTime: LocalDateTime) {
        val movieTicket =
            movieTicketService.createMovieTicket(movieId, screeningDateTime, getHeadCount())
        view.navigateToSummary(movieTicket)
    }

    override fun loadAvailableDates(selectedDate: LocalDate) {
        val bookableDates = movieScheduler.getBookableDates()
        val index = bookableDates.indexOf(selectedDate).takeIf { it != -1 } ?: 0
        view.updateDateSpinner(bookableDates, index)
    }

    override fun loadAvailableTimes(
        selectedDate: LocalDate,
        selectedTime: LocalTime,
    ) {
        val times = movieScheduler.getBookableTimes(selectedDate)
        val index = times.indexOf(selectedTime).takeIf { it != -1 } ?: 0
        view.updateTimeSpinner(times, index)
    }
}
