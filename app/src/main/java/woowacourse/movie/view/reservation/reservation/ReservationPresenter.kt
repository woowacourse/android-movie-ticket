package woowacourse.movie.view.reservation.reservation

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import woowacourse.movie.model.ReservationState
import woowacourse.movie.model.TicketCount
import woowacourse.movie.view.ReservationUiFormatter
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationPresenter(
    val view: ReservationContract.View,
) : ReservationContract.Presenter {
    private lateinit var reservationState: ReservationState

    override fun fetchData(getMovie: () -> Movie?) {
        val result = getMovie()
        if (result == null) {
            view.showErrorDialog()
            return
        }

        reservationState =
            ReservationState(
                movie = result,
                movieDate = MovieDate(result.startDate, result.endDate),
                movieTime = MovieTime(),
                ticketCount = TicketCount(),
                timeTable = emptyList(),
            )

        updateMovieInfo()
    }

    override fun initDateAdapter() {
        var duration = reservationState.movieDate.getDateTable(LocalDate.now())
        if (duration.isEmpty()) duration = listOf(reservationState.movie.startDate)

        view.updateDateAdapter(duration, 0)
        onDateSelected(duration[0])
    }

    override fun onDateSelected(date: LocalDate) {
        reservationState.movieDate.updateDate(date)
        updateReservationState(
            movieDate = reservationState.movieDate,
            timeTable = reservationState.movieTime.getTimeTable(LocalDateTime.now(), date),
        )
        view.updateTimeAdapter(
            reservationState.timeTable.map {
                ReservationUiFormatter.movieTimeToUI(
                    it,
                )
            },
        )
    }

    override fun onTimeSelected(position: Int) {
        reservationState.movieTime.updateTime(reservationState.timeTable[position])
        updateReservationState(movieTime = reservationState.movieTime)
    }

    override fun plusTicketCount() {
        updateReservationState(
            ticketCount = reservationState.ticketCount.plus(1),
        )
        view.setTicketCount(reservationState.ticketCount.value)
    }

    override fun minusTicketCount() {
        updateReservationState(
            ticketCount = reservationState.ticketCount.minus(1),
        )
        view.setTicketCount(reservationState.ticketCount.value)
    }

    override fun createTicket(onCreated: (MovieTicket) -> Unit) {
        val ticket =
            MovieTicket(
                title = reservationState.movie.title,
                date = reservationState.movieDate.value,
                time = ReservationUiFormatter.movieTimeToUI(reservationState.movieTime.value),
                count = reservationState.ticketCount.value,
            )
        onCreated(ticket)
    }

    fun restoreTicketCount(count: Int) {
        updateReservationState(
            ticketCount = TicketCount(count),
        )
        view.setTicketCount(reservationState.ticketCount.value)
    }

    fun currentTicketCount(): Int = reservationState.ticketCount.value

    private fun updateMovieInfo() {
        val movie = reservationState.movie
        view.updateMovieInfo(
            posterResId = movie.poster,
            title = movie.title,
            startDate = ReservationUiFormatter.localDateToUI(movie.startDate),
            endDate = ReservationUiFormatter.localDateToUI(movie.endDate),
            runningTime = movie.runningTime,
        )
    }

    private fun updateReservationState(
        movieDate: MovieDate = reservationState.movieDate,
        movieTime: MovieTime = reservationState.movieTime,
        ticketCount: TicketCount = reservationState.ticketCount,
        timeTable: List<Int> = reservationState.timeTable,
    ) {
        reservationState =
            reservationState.copy(
                movieDate = movieDate,
                movieTime = movieTime,
                ticketCount = ticketCount,
                timeTable = timeTable,
            )
    }
}
