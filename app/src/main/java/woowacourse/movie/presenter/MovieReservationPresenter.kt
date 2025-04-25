package woowacourse.movie.presenter

import woowacourse.movie.MovieReservationContract
import woowacourse.movie.domain.Scheduler
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.view.model.MovieUiModel
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
    private lateinit var ticket: Ticket
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
        view.showScreeningTimes(screeningTimes)

        ticket =
            Ticket(
                movie.toDomain(),
                LocalDateTime.of(
                    screeningDates.first(),
                    screeningTimes.first(),
                ),
                TicketCount.of(TicketCount.MIN_COUNT),
            )

        view.showReservationInfo(ticket.toUiModel())
    }

    override fun onDateSelection(date: LocalDate) {
        val screeningTimes: List<LocalTime> = scheduler.getShowtimes(date, LocalDateTime.now())
        view.showScreeningTimes(screeningTimes)
        ticket = ticket.copy(showtime = LocalDateTime.of(date, ticket.showtime.toLocalTime()))
    }

    override fun onTimeSelection(time: LocalTime) {
        ticket = ticket.copy(showtime = LocalDateTime.of(ticket.showtime.toLocalDate(), time))
    }

    override fun incrementTicketCount() {
        ticket = ticket.increment()
        view.showTicketCount(ticket.count.value)
    }

    override fun decrementTicketCount() {
        ticket = ticket.decrement()
        view.showTicketCount(ticket.count.value)
    }

    override fun onReservation() {
        view.showAlertDialog()
    }

    override fun onReservationConfirmation() {
        view.confirmReservation(ticket.toUiModel())
    }
}
