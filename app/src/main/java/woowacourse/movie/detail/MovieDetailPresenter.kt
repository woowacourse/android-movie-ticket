package woowacourse.movie.detail

import woowacourse.movie.db.MediaContents
import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.InRange
import woowacourse.movie.model.OutOfRange
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Ticket
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    private val movieId: Int,
    val ticket: Ticket = Ticket(),
    private val reservationSchedule: ReservationSchedule = ReservationSchedule(),
) : MovieDetailContract.Presenter {
    init {
        loadMovie()
        loadScreeningDates()
    }

    override fun loadSavedData() {
        view.showCount(ticket.count)
    }

    override fun loadMovie() {
        view.showMovieInformation(MediaContents.obtainMovie(movieId))
    }

    override fun updateScreeningDate(screeningDate: LocalDate) {
        reservationSchedule.updateScreeningDate(screeningDate)

        view.showScreeningTimes(
            reservationSchedule.obtainScreeningTimes(screeningDate).map { "$it:00" })
    }

    override fun updateScreeningTime(screeningTime: String) {
        val (hour, minute) = screeningTime.split(":").map(String::toInt)

        reservationSchedule.updateScreeningTime(LocalTime.of(hour % 24, minute))
    }

    override fun loadScreeningDates() {
        val movie = MediaContents.obtainMovie(movieId)

        view.showScreeningDates(
            reservationSchedule.obtainScreeningDates(
                movie.firstScreeningDate,
                movie.lastScreeningDate,
            ),
        )
    }

    override fun increaseCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun decreaseCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result)
    }

    override fun deliverReservationInformation() {
        val movieTitle = MediaContents.obtainMovie(movieId).title

        view.moveToSeatSelect(movieTitle, ticket, reservationSchedule)
    }

    private fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult) {
        when (result) {
            is InRange -> {
                view.showCount(ticket.count)
            }

            is OutOfRange -> view.showErrorToast()
        }
    }
}
