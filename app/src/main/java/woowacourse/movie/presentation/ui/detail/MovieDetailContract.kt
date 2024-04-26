package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.presentation.uimodel.MovieUiModel
import java.time.LocalDate

interface MovieDetailContract {
    interface View {
        fun initSpinnerAdapter(list: List<LocalDate>)

        fun showMovieDetail(movieUiModel: MovieUiModel)

        fun showReservationCount(count: Int)

        fun moveToReservationPage(movieTicketId: Int)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadMovieDetails(movieId: Int)

        fun loadMovieScheduleDates(dates: List<LocalDate>)

        fun minusReservationCount()

        fun plusReservationCount()

        fun updateReservationCount(count: Int)

        fun reservationCount(): Int

        fun requestReservationResult()
    }
}
