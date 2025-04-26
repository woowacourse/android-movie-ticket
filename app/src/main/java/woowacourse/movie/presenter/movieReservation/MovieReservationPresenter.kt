package woowacourse.movie.presenter.movieReservation

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

class MovieReservationPresenter(
    private val view: MovieReservationActivity,
) : MovieReservationContract.Presenter {
    private lateinit var _ticket: Ticket
    val ticket get() = _ticket.toUiModel()
    private val scheduler = Scheduler()

    override fun loadReservationInfo() {
        val movie =
            view.intent.extras?.getParcelableCompat<MovieUiModel>(KEY_MOVIE)
                ?: run { return }

        val screeningDates: List<LocalDate> =
            scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDateTime.now())
        view.showScreeningDates(screeningDates)

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

        view.showReservationInfo(_ticket.toUiModel())
    }

    override fun onInstanceStateRestored(ticket: TicketUiModel) {
        _ticket = ticket.toDomain()
        val selectedDate = ticket.showtime.toLocalDate()
        val screeningTimes: List<LocalTime> =
            scheduler.getShowtimes(selectedDate, LocalDateTime.now())
        view.setTimeSpinner(screeningTimes.indexOf(ticket.showtime.toLocalTime()))
        view.showReservationInfo(_ticket.toUiModel())
    }

    override fun onDateSelection(date: LocalDate) {
        val screeningTimes: List<LocalTime> = scheduler.getShowtimes(date, LocalDateTime.now())
        view.showScreeningTimes(screeningTimes, ticket.showtime.toLocalTime())
        _ticket = _ticket.copy(showtime = LocalDateTime.of(date, ticket.showtime.toLocalTime()))
    }

    override fun onTimeSelection(time: LocalTime) {
        _ticket = _ticket.copy(showtime = LocalDateTime.of(ticket.showtime.toLocalDate(), time))
    }

    override fun incrementTicketCount() {
        _ticket = _ticket.increment()
        view.showTicketCount(ticket.count)
    }

    override fun decrementTicketCount() {
        _ticket = _ticket.decrement()
        view.showTicketCount(ticket.count)
    }

    override fun onReservation() {
        view.showAlertDialog()
    }

    override fun onReservationConfirmation() {
        view.confirmReservation(_ticket.toUiModel())
    }
}
