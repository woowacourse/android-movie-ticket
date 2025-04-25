package woowacourse.movie.view.reservation

import woowacourse.movie.domain.Scheduler
import woowacourse.movie.view.reservation.model.TicketUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private var _ticket: TicketUiModel,
    private val scheduler: Scheduler = Scheduler(),
) : MovieReservationContract.Presenter {
    val ticket: TicketUiModel
        get() = _ticket

    override fun loadMovieReservationScreen() {
        view.showMovieInfo(ticket.movie)
        view.showHeadCount(ticket.count)
        loadScreeningDates()
        loadShowtimes()
    }

    override fun onClickIncrementButton() {
        _ticket = ticket.copy(count = ticket.count + 1)
        view.showHeadCount(ticket.count)
    }

    override fun onClickDecrementButton() {
        _ticket = ticket.copy(count = ticket.count - 1)
        view.showHeadCount(ticket.count)
    }

    override fun onSelectDate(date: LocalDate) {
        _ticket = ticket.copy(showtime = LocalDateTime.of(date, ticket.showtime.toLocalTime()))

        val showtimes = scheduler.getShowtimes(date, LocalDateTime.now())
        view.updateTimeSpinner(showtimes, ticket.showtime.toLocalTime())
    }

    override fun onSelectTime(time: LocalTime) {
        _ticket = ticket.copy(showtime = LocalDateTime.of(ticket.showtime.toLocalDate(), time))
    }

    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }

    private fun loadScreeningDates() {
        val screeningDates =
            scheduler.getScreeningDates(
                ticket.movie.startDate,
                ticket.movie.endDate,
                LocalDate.now(),
            )
        val selectedDate = ticket.showtime.toLocalDate()
        view.updateDateSpinner(screeningDates, selectedDate)
    }

    private fun loadShowtimes() {
        val showtimes =
            scheduler.getShowtimes(ticket.showtime.toLocalDate(), LocalDateTime.now())
        view.updateTimeSpinner(showtimes, ticket.showtime.toLocalTime())
    }
}
