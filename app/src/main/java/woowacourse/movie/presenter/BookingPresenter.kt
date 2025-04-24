package woowacourse.movie.presenter

import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.schedule.MovieScheduler
import woowacourse.movie.domain.service.MovieTicketService
import woowacourse.movie.ui.view.booking.BookingContract
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenter(
    private val view: BookingContract.View,
    private val movieScheduler: MovieScheduler,
    private var headCount: HeadCount = HeadCount(),
    private val movieTicketService: MovieTicketService = MovieTicketService(),
) : BookingContract.Presenter {
    override fun getHeadCount(): Int = headCount.getCount()

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

    override fun restoreHeadCount(restoredCount: Int) {
        headCount = HeadCount(restoredCount)
    }

    override fun onConfirm(
        id: Int,
        screeningDateTime: LocalDateTime,
    ) {
        val movieTicket =
            movieTicketService.createMovieTicket(id, screeningDateTime, headCount.getCount())
        view.navigateToSummary(movieTicket)
    }

    override fun loadAvailableDates(
        startDate: LocalDate,
        endDate: LocalDate,
        selectedDate: LocalDate,
    ) {
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
