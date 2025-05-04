package woowacourse.movie.ui.booking

import woowacourse.movie.domain.movies.MovieRepository
import woowacourse.movie.domain.movies.MovieReserveService
import woowacourse.movie.domain.movies.MovieScheduler
import woowacourse.movie.domain.ticket.HeadCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenter(
    private val view: BookingContract.View,
    private val movieId: Int,
    private val movieReserveService: MovieReserveService = MovieReserveService(),
) : BookingContract.Presenter {
    private val movie = MovieRepository().getMovieById(movieId)
    private val movieScheduler = MovieScheduler(movie.startScreeningDate, movie.endScreeningDate)
    private var headCount: HeadCount = HeadCount()
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null

    override fun loadInitialHeadCount() {
        view.updateHeadCount(headCount.value)
    }

    override fun increaseHeadCount() {
        headCount += 1
        view.updateHeadCount(headCount.value)
    }

    override fun decreaseHeadCount() {
        headCount -= 1
        view.updateHeadCount(headCount.value)
    }

    override fun restoreHeadCount(restoredCount: Int) {
        headCount = HeadCount(restoredCount)
    }

    override fun restoreDate(restoredDate: LocalDate?) {
        selectedDate = restoredDate
    }

    override fun restoreTime(restoredTime: LocalTime?) {
        selectedTime = restoredTime
    }

    override fun onConfirm() {
        val reservedMovie =
            movieReserveService.createMovieToReserve(
                movieId,
                LocalDateTime.of(selectedDate, selectedTime),
                headCount.value,
            )
        view.navigateToSeatsSelection(reservedMovie)
    }

    override fun loadAvailableDates() {
        val bookableDates = movieScheduler.getBookableDates()
        val index = bookableDates.indexOf(selectedDate).takeIf { it != -1 } ?: 0
        view.updateDateSpinner(bookableDates, index)
    }

    override fun loadAvailableTimes(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
        val times = movieScheduler.getBookableTimes(selectedDate)
        val index = times.indexOf(selectedTime).takeIf { it != -1 } ?: 0
        view.updateTimeSpinner(times, index)
    }

    override fun loadSelectedMovie() {
        view.showSelectedMovie(movie)
    }
}
