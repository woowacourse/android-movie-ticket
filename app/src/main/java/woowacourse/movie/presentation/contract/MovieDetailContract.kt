package woowacourse.movie.presentation.contract

import woowacourse.movie.presentation.uimodel.MovieUiModel

interface MovieDetailContract {
    interface View {
        fun showMovieDetail(movieUiModel: MovieUiModel)

        fun showReservationCount(count: Int)

        fun moveToReservationResult(
            title: String,
            screeningStartDate: String,
            reservationCount: Int,
            totalPrice: Int,
        )
    }

    interface Presenter {
        fun attachView(view: View)

        fun detachView()

        fun onViewSetUp()

        fun minusReservationCount()

        fun plusReservationCount()

        fun initReservationCount(count: Int)

        fun onReserveButtonClicked()
    }
}
