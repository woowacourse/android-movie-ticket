package woowacourse.movie.feature.movieReservation

import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.movie.TicketCount
import woowacourse.movie.domain.schedule.Scheduler
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.toDomain
import woowacourse.movie.feature.model.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private lateinit var _ticket: Ticket
    val ticket get() = _ticket.toUiModel()
    private val scheduler = Scheduler()

    override fun loadReservationInfo(movie: MovieUiModel) {
        initializeInfo(movie)
        view.showReservationInfo(ticket)
        view.updateTicketCount(ticket.count)
        setControlsStatus()
    }

    private fun initializeInfo(movie: MovieUiModel) {
        val screeningDates: List<LocalDate> =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDateTime.now())
        view.loadSpinnerDates(screeningDates)

        val screeningTimes: List<LocalTime> =
            scheduler.getShowtimes(screeningDates.first(), LocalDateTime.now())

        _ticket =
            Ticket(
                movie.toDomain(),
                LocalDateTime.of(
                    screeningDates.first(),
                    screeningTimes.first(),
                ),
                TicketCount.of(TicketCount.MIN_COUNT),
            )
    }

    override fun restoreReservationInfo(ticket: TicketUiModel) {
        _ticket = ticket.toDomain()
        val selectedDate = ticket.showtime.toLocalDate()
        val screeningTimes: List<LocalTime> =
            scheduler.getShowtimes(selectedDate, LocalDateTime.now())
        view.setTimeSpinner(screeningTimes.indexOf(ticket.showtime.toLocalTime()))
        view.updateTicketCount(ticket.count)
        setControlsStatus()
    }

    override fun selectDate(date: LocalDate) {
        val screeningTimes: List<LocalTime> = scheduler.getShowtimes(date, LocalDateTime.now())
        view.loadSpinnerTimes(screeningTimes, ticket.showtime.toLocalTime())
        _ticket = _ticket.copy(showtime = LocalDateTime.of(date, ticket.showtime.toLocalTime()))
    }

    override fun selectTime(time: LocalTime) {
        _ticket = _ticket.copy(showtime = LocalDateTime.of(ticket.showtime.toLocalDate(), time))
    }

    override fun incrementTicketCount() {
        _ticket = _ticket.increment()
        view.updateTicketCount(ticket.count)
        setControlsStatus()
    }

    override fun decrementTicketCount() {
        _ticket = _ticket.decrement()
        view.updateTicketCount(ticket.count)
        setControlsStatus()
    }

    private fun setControlsStatus() {
        view.setIncrementEnabled(_ticket.count.canIncrement())
        view.setDecrementEnabled(_ticket.count.canDecrement())
    }

    override fun confirmSelection() {
        view.goToSeatSelection(_ticket.toUiModel())
    }
}
