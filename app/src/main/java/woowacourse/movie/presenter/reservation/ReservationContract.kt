package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket
import java.time.LocalDate

interface ReservationContract {
    interface View {
        fun setupDateAdapter(dates: List<LocalDate>)

        fun setupTimeAdapter(dates: List<Int>)

        fun showTicketCount(count: Int)

        fun showTitle(title: String)

        fun showScreeningDate(
            startDate: LocalDate,
            endDate: LocalDate,
        )

        fun showPoster(poster: Int)

        fun showRunningTime(runningTime: Int)

        fun showErrorToastMessage(message: String)

        fun showReservationCompleteView(movieTicket: MovieTicket)

        fun updateTimes(times: List<Int>)
    }

    interface Presenter {
        fun updateMovieData(movie: Movie)

        fun increaseTicketCount()

        fun decreaseTicketCount()

        fun createMovieTicket()

        fun updateMovieDate(date: LocalDate)

        fun updateMovieTime(time: Int)

        fun updateTicketCount(count: Int?)
    }
}
