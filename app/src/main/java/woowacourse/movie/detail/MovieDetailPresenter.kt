package woowacourse.movie.detail

import woowacourse.movie.db.MediaContents
import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.InRange
import woowacourse.movie.model.OutOfRange
import woowacourse.movie.model.Ticket
import java.time.LocalDate

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    private val movieId: Int,
    val ticket: Ticket = Ticket(),
) : MovieDetailContract.Presenter {
    init {
        loadMovie()
        loadScreeningDates()
    }

    override fun loadSavedData() {
        view.updateCount(ticket.count)
    }

    override fun loadMovie() {
        view.showMovieInformation(MediaContents.obtainMovie(movieId))
    }

    override fun loadScreeningTimes(date: LocalDate) {
        view.showScreeningTimes(ticket.obtainScreeningTimes(date).map { "$it:00" })
    }

    override fun updateScreeningDate(screeningDate: String) {
        ticket.updateScreeningDate(screeningDate)
    }

    override fun updateScreeningTime(screeningTime: String) {
        ticket.updateScreeningTime(screeningTime)
    }

    override fun loadScreeningDates() {
        val movie = MediaContents.obtainMovie(movieId)

        view.showScreeningDates(
            ticket.obtainScreeningDates(
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

        view.moveToSeatSelect(movieTitle, ticket)
    }

    private fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult) {
        when (result) {
            is InRange -> {
                view.updateCount(ticket.count)
            }

            is OutOfRange -> view.showErrorToast()
        }
    }
}
