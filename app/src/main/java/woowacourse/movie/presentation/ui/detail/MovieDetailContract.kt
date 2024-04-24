package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.presentation.uimodel.MovieUiModel

interface MovieDetailContract {
    interface View {
        fun showMovieDetail(movieUiModel: MovieUiModel)

        fun showReservationCount(count: Int)

        fun moveToReservationPage(movieTicketId: Int)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadMovieDetailsAndSetupTicket(movieId: Int)

        fun minusReservationCount()

        fun plusReservationCount()

        fun updateReservationCount(reservationCount: Int)

        fun requestReservationResult()
    }
}
