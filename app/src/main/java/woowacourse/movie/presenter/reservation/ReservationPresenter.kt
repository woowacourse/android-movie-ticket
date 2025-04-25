package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import woowacourse.movie.model.TicketCount
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.reservation.ReservationContract
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationPresenter(
    val view: ReservationContract.View,
) : ReservationContract.Presenter {
    private lateinit var movie: Movie
    private lateinit var movieDate: MovieDate
    private var timeTable: List<Int> = emptyList()
    private val movieTime = MovieTime()
    private val formatter = ReservationUiFormatter()
    private var ticketCount = TicketCount()
    private var selectedDatePosition: Int = 0

    override fun fetchData(getMovie: () -> Movie?) {
        val result = getMovie()
        if (result == null) {
            view.showErrorDialog()
            return
        }

        movie = result
        movieDate = MovieDate(movie.startDate, movie.endDate)
        view.updateMovieInfo(
            posterResId = movie.poster,
            title = movie.title,
            startDate = formatter.localDateToUI(movie.startDate),
            endDate = formatter.localDateToUI(movie.endDate),
            runningTime = movie.runningTime,
        )
    }

    override fun initDateAdapter() {
        var duration = movieDate.getDateTable(LocalDate.now())
        if (duration.isEmpty()) duration = listOf(movie.startDate)

        view.updateDateAdapter(duration, 0)
        onDateSelected(duration[0], selectedDatePosition)
    }

    override fun onDateSelected(
        date: LocalDate,
        position: Int,
    ) {
        movieDate.updateDate(date)
        timeTable = movieTime.getTimeTable(LocalDateTime.now(), date)
        selectedDatePosition = position
        view.updateTimeAdapter(timeTable.map { ReservationUiFormatter().movieTimeToUI(it) })
    }

    override fun onTimeSelected(position: Int) {
        movieTime.updateTime(timeTable[position])
    }

    override fun plusTicketCount() {
        ticketCount = ticketCount.plus(1)
        view.setTicketCount(ticketCount.value)
    }

    override fun minusTicketCount() {
        ticketCount = ticketCount.minus(1)
        view.setTicketCount(ticketCount.value)
    }

    override fun createTicket(onCreated: (MovieTicket) -> Unit) {
        val ticket =
            MovieTicket(
                title = movie.title,
                date = movieDate.value,
                time = formatter.movieTimeToUI(movieTime.value),
                count = ticketCount.value,
            )
        onCreated(ticket)
    }

    fun currentDatePosition(): Int = selectedDatePosition

    fun restoreTicketCount(saved: Int) {
        ticketCount = TicketCount(saved)
    }

    fun currentTicketCount(): Int = ticketCount.value
}
