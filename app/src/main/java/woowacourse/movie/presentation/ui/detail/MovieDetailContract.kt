package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.presentation.uimodel.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

interface MovieDetailContract {
    interface View {
        fun updateSpinnerAdapter(list: List<LocalDate>)

        fun updateTimeSpinnerAdapter(list: List<LocalTime>)

        fun showMovieDetail(movieUiModel: MovieUiModel)

        fun showReservationCount(count: Int)

        fun moveToReservationPage(movieTicketId: Int)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadMovieDetails(movieId: Int)

        fun loadMovieScheduleDates(dates: List<LocalDate>)

        fun updateMovieScheduleDate(date: LocalDate)

        fun updateMovieScheduleTime(time: LocalTime)

        fun minusReservationCount()

        fun plusReservationCount()

        fun updateReservationCount(count: Int)

        fun reservationCount(): Int

        fun requestReservationResult()
    }
}
