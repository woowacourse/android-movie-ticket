package woowacourse.movie.reservation.detail

import woowacourse.movie.model.Movie
import java.time.LocalDate

interface ReservationDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun updateCount(ticketCount: Int)

        fun showScreeningDates(screeningDates: List<LocalDate>)

        fun showErrorToast()

        fun moveToReservationFinished(
            movieId: Int,
            ticketCount: Int,
        )
    }

    interface Presenter {
        fun increaseCount()

        fun decreaseCount()

        fun loadMovie()

        fun loadScreeningDates()

        fun deliverReservationInformation()
    }
}
