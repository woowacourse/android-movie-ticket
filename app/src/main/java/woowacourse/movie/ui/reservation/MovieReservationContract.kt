package woowacourse.movie.ui.reservation

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.ReservationCount
import woowacourse.movie.ui.HandleError
import java.time.LocalDate

interface MovieReservationContract {
    interface View : HandleError {
        fun showMovieContent(movieContent: MovieContent)

        fun updateReservationCount(reservationCount: Int)

        fun showMovieDateSelection(dateRange: List<LocalDate>)

        fun showMovieTimeSelection(timeRange: List<String>)

        fun moveMovieReservationCompleteView(
            reservationCount: Int,
            selectedDate: String,
            selectedTime: String,
        )
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateReservationCount(count: Int = ReservationCount.DEFAULT_VALUE)

        fun selectDate(date: LocalDate)

        fun selectTime(time: String)

        fun decreaseCount()

        fun increaseCount()

        fun reserveMovie()

        fun handleError(throwable: Throwable)
    }
}
