package woowacourse.movie.presentation.screen.detail

import woowacourse.movie.model.Ticket
import java.time.LocalDate
import java.time.LocalTime

interface MovieDetailContract {
    interface View {
        fun setUpView(
            img: Int,
            title: String,
            screenDate: String,
            runningTime: Int,
            description: String,
        )

        fun setUpSpinner(
            dates: List<LocalDate>,
            times: List<LocalTime>,
        )

        fun updateTicketCount(count: Int)

        fun updateTimeSpinner(times: List<LocalTime>)

        fun navigateToReservationSeat(movieId: Int, ticket: Ticket)
    }

    interface Presenter {
        fun fetchScreenInfo(movieId: Int)

        fun subTicketCount()

        fun addTicketCount()

        fun ticketCount(): Int

        fun restoreTicketCount(count: Int)

        fun onSelectedDateTime(date: LocalDate)

        fun createTicket()

        fun registerScreenDate(date: LocalDate)

        fun registerScreenTime(time: LocalTime)

        fun loadMovieDetail()
    }
}
