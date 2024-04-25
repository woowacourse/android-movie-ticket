package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.Movie
import woowacourse.movie.model.ScreeningDateTime
import woowacourse.movie.model.Ticket

interface ReservationDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun showScreeningPeriod(movie: Movie)

        fun showScreeningTimes(
            movie: Movie,
            selectedDate: String,
        )

        fun changeHeadCount(count: Int)

        fun showResultToast()

        fun navigateToSeatSelection(
            movieId: Int,
            ticket: Ticket,
        )
    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadScreeningPeriod(movieId: Int)

        fun loadScreeningTimes(
            movieId: Int,
            selectedDate: String,
        )

        fun increaseTicketCount(count: Int)

        fun decreaseTicketCount(count: Int)

        fun initializeReservationButton(
            movieId: Int,
            dateTime: ScreeningDateTime,
        )

        fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult)
    }
}
