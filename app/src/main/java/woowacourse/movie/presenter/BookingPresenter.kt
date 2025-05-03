package woowacourse.movie.presenter

import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.schedule.MovieScheduler
import woowacourse.movie.domain.service.ReservationService
import woowacourse.movie.ui.view.booking.BookingContract
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenter(
    private val view: BookingContract.View,
    private val movieId: Int,
    private var headCount: HeadCount = HeadCount(),
    movieRepository: MovieRepository = MovieRepository(),
    private val reservationService: ReservationService = ReservationService(),
) : BookingContract.Presenter {
    private val movie = movieRepository.getMovieById(movieId)
    private val movieScheduler = MovieScheduler(movie.startScreeningDate, movie.endScreeningDate)
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null

    override fun loadInitialHeadCount() {
        view.updateHeadCount(headCount.getCount())
    }

    override fun increaseHeadCount() {
        headCount.increase()
        view.updateHeadCount(headCount.getCount())
    }

    override fun decreaseHeadCount() {
        headCount.decrease()
        view.updateHeadCount(headCount.getCount())
    }

    override fun saveHeadCount(restoredCount: Int) {
        headCount = HeadCount(restoredCount)
    }

    override fun saveDate(restoredDate: LocalDate?) {
        selectedDate = restoredDate
    }

    override fun saveTime(restoredTime: LocalTime?) {
        selectedTime = restoredTime
    }

    override fun onConfirm() {
        val reservedMovie =
            reservationService.reserveMovie(
                movieId,
                LocalDateTime.of(selectedDate, selectedTime),
                headCount.getCount(),
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
