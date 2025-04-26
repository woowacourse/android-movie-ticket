package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.MovieToReserve
import java.time.LocalDate

interface ReservationContract {
    interface View {
        fun setupDateAdapter(dates: List<LocalDate>)

        fun showTicketCount(count: Int)

        fun showTitle(title: String)

        fun showScreeningDate(
            startDate: LocalDate,
            endDate: LocalDate,
        )

        fun showPoster(poster: Int)

        fun showRunningTime(runningTime: Int)

        fun showErrorToastMessage(message: String)

        fun showSeatSelectionView(movieToReserve: MovieToReserve)

        fun updateTimes(times: List<Int>)

        fun showSelectedDate(position: Int)

        fun showSelectedTime(position: Int)
    }

    interface Presenter {
        fun updateMovieData(movie: Movie)

        fun increaseTicketCount()

        fun decreaseTicketCount()

        fun updateMovieReservationInfo()

        fun updateMovieDate(date: LocalDate)

        fun updateMovieTime(time: Int)

        fun updateTicketCount(count: Int?)

        fun updateSelectedDatePosition(position: Int)

        fun updateSelectedTimePosition(position: Int)
    }
}
