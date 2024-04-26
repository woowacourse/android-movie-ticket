package woowacourse.movie.detail

import woowacourse.movie.model.Movie
import java.time.LocalDate

interface MovieDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun updateCount(ticketCount: Int)

        fun showScreeningDates(screeningDates: List<LocalDate>)

        fun showErrorToast()

        fun moveToSeatSelect(
            movieTitle: String,
            ticketCount: Int,
            screeningDate: String,
            screeningTime: String,
        )

        fun showScreeningTimes(screeningTimes: List<String>)
    }

    interface Presenter {
        fun increaseCount()

        fun decreaseCount()

        fun loadMovie()

        fun loadScreeningDates()

        fun loadScreeningTimes(date: LocalDate)

        fun updateScreeningDate(screeningDate: String)

        fun updateScreeningTime(screeningTime: String)

        fun deliverReservationInformation()
    }
}
