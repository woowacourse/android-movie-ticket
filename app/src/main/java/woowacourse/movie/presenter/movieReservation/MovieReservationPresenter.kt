package woowacourse.movie.presenter.movieReservation

import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.movie.TicketCount
import woowacourse.movie.domain.schedule.Scheduler
import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.toDomain
import woowacourse.movie.view.model.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private lateinit var _ticket: Ticket
    val ticket get() = _ticket.toUiModel()
    private val scheduler = Scheduler()

    override fun onViewCreated(movie: MovieUiModel) {
        initializeInfo(movie)
        view.showMoviePoster(ticket.movie.poster)
        view.showMovieTitle(ticket.movie.title)
        view.showScreeningDates(ticket.movie.startDate, ticket.movie.endDate)
        view.showRunningTime(ticket.movie.runningTime)
        view.showTicketCount(ticket.count.toString())
    }

    private fun initializeInfo(movie: MovieUiModel) {
        val screeningDates: List<LocalDate> =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDateTime.now())
        view.showSpinnerDates(screeningDates)

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

    override fun onInstanceStateRestored(ticket: TicketUiModel) {
        _ticket = ticket.toDomain()
        val selectedDate = ticket.showtime.toLocalDate()
        val screeningTimes: List<LocalTime> =
            scheduler.getShowtimes(selectedDate, LocalDateTime.now())
        view.setTimeSpinner(screeningTimes.indexOf(ticket.showtime.toLocalTime()))
        view.showTicketCount(ticket.count.toString())
    }

    override fun onDateSelection(date: LocalDate) {
        val screeningTimes: List<LocalTime> = scheduler.getShowtimes(date, LocalDateTime.now())
        view.showSpinnerTimes(screeningTimes, ticket.showtime.toLocalTime())
        _ticket = _ticket.copy(showtime = LocalDateTime.of(date, ticket.showtime.toLocalTime()))
    }

    override fun onTimeSelection(time: LocalTime) {
        _ticket = _ticket.copy(showtime = LocalDateTime.of(ticket.showtime.toLocalDate(), time))
    }

    override fun onTicketCountIncrement() {
        _ticket = _ticket.increment()
        view.showTicketCount(ticket.count.toString())
    }

    override fun onTicketCountDecrement() {
        _ticket = _ticket.decrement()
        view.showTicketCount(ticket.count.toString())
    }

    override fun onTicketCountChange() {
        view.setIncrementEnabled(_ticket.count.canIncrement())
        view.setDecrementEnabled(_ticket.count.canDecrement())
    }

    override fun onConfirmSelection() {
        view.goToSeatSelection(_ticket.toUiModel())
    }
}
