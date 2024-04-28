package woowacourse.movie.presentation.reservation

import android.os.Bundle
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.model.MovieDateModel
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.TicketModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        fun showMovie(movie: Movie)

        fun showCurrentResultTicketCountView()

        fun showDate(dates: List<LocalDate>)

        fun showTime(times: List<LocalTime>)

        fun moveToSeatSelection(pendingMovieReservation: PendingMovieReservationModel)
    }

    interface Presenter {
        fun loadMovie()

        fun loadDate(
            startDate: LocalDate,
            endDate: LocalDate,
        )

        fun loadTime(currentDate: LocalDate)

        fun decreaseTicketCount()

        fun increaseTicketCount()

        fun getTicketCount(): Int

        fun reservation(
            title: String,
            count: Int,
        )

        fun selectDate(newDate: LocalDate)

        fun selectTime(newTime: LocalTime)

        fun initSavedInstance(
            count: Int,
            movieDateModel: MovieDateModel,
        )

        fun saveInstance(outState: Bundle)
    }
}
