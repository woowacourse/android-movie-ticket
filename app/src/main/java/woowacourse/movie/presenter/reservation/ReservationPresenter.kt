package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import woowacourse.movie.model.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationPresenter(
    private val view: ReservationContract.View,
) : ReservationContract.Presenter {
    private lateinit var movie: Movie
    private var ticketCount = TicketCount()
    private val movieDate by lazy { MovieDate(movie.startDate, movie.endDate) }
    private val movieTime by lazy { MovieTime() }

    override fun updateMovieData(movie: Movie) {
        this.movie = movie
        view.showTitle(movie.title)
        view.showPoster(movie.poster)
        view.showRunningTime(movie.runningTime)
        view.showScreeningDate(movie.startDate, movie.endDate)
        view.showTicketCount(ticketCount.value)
        view.setupDateAdapter(movieDate.getDateTable(LocalDate.now()))
        view.updateTimes(movieTime.getTimeTable(LocalDateTime.now(), movieDate.value))
    }

    override fun increaseTicketCount() {
        ticketCount += 1
        view.showTicketCount(ticketCount.value)
    }

    override fun decreaseTicketCount() {
        runCatching {
            ticketCount -= 1
        }.onSuccess {
            view.showTicketCount(ticketCount.value)
        }.onFailure { error ->
            view.showErrorToastMessage(error.message.toString())
        }
    }

    override fun createMovieTicket() {
        val movieTicket =
            MovieTicket(
                movie.title,
                movieDate.value,
                movieTime,
                ticketCount,
            )
        view.showReservationCompleteView(movieTicket)
    }

    override fun updateMovieDate(date: LocalDate) {
        movieDate.updateDate(date)
        view.updateTimes(movieTime.getTimeTable(LocalDateTime.now(), movieDate.value))
    }

    override fun updateMovieTime(time: Int) {
        movieTime.updateTime(time)
    }

    override fun updateTicketCount(count: Int?) {
        if (count != null) {
            ticketCount += count - 1
        }
        view.showTicketCount(ticketCount.value)
    }

    override fun updateSelectedDatePosition(position: Int) {
        view.showSelectedDate(position)
    }

    override fun updateSelectedTimePosition(position: Int) {
        view.showSelectedTime(position)
    }
}
