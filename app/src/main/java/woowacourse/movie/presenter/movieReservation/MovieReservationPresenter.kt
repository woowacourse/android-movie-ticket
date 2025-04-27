package woowacourse.movie.presenter.movieReservation

import woowacourse.movie.R
import woowacourse.movie.domain.Scheduler
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.view.model.MovieListItem.MovieUiModel
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.model.toDomain
import woowacourse.movie.view.model.toUiModel
import woowacourse.movie.view.movieReservation.MovieReservationActivity
import woowacourse.movie.view.movieReservation.MovieReservationActivity.Companion.KEY_MOVIE
import woowacourse.movie.view.utils.getParcelableCompat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieReservationPresenter(
    private val view: MovieReservationActivity,
) : MovieReservationContract.Presenter {
    private lateinit var _ticket: Ticket
    val ticket get() = _ticket.toUiModel()
    private val scheduler = Scheduler()

    override fun onViewCreated() {
        initializeInfo()

        val dateTimeFormatter = DateTimeFormatter.ofPattern(view.getString(R.string.date_format))
        val screeningDatesTemplate = view.getString(R.string.screening_dates_format)
        val runningTimeTemplate = view.getString(R.string.running_type_format)
        val startDate = _ticket.movie.startDate.format(dateTimeFormatter)
        val endDate = _ticket.movie.endDate.format(dateTimeFormatter)

        view.showMoviePoster(ticket.movie.poster)
        view.showMovieTitle(ticket.movie.title)
        view.showScreeningDates(screeningDatesTemplate.format(startDate, endDate))
        view.showRunningTime(runningTimeTemplate.format(_ticket.movie.runningTime))
        view.showTicketCount(ticket.count.toString())
    }

    private fun initializeInfo() {
        val movie = view.intent.extras?.getParcelableCompat<MovieUiModel>(KEY_MOVIE) ?: return

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
