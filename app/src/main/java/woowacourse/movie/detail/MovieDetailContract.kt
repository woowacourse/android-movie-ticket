package woowacourse.movie.detail

import woowacourse.movie.model.Movie
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Ticket
import java.time.LocalDate

interface MovieDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun showCount(count: Int)

        fun showScreeningDates(screeningDates: List<LocalDate>)

        fun moveToSeatSelect(
            movieTitle: String,
            ticket: Ticket,
            reservationSchedule: ReservationSchedule,
        )

        fun showScreeningTimes(screeningTimes: List<String>)
    }

    interface Presenter {
        fun loadSavedData()

        fun loadScreeningDates()

        fun loadMovie()

        fun increaseCount()

        fun decreaseCount()

        fun updateScreeningDate(screeningDate: LocalDate)

        fun updateScreeningTime(screeningTime: String)

        fun deliverReservationInformation()
    }
}
