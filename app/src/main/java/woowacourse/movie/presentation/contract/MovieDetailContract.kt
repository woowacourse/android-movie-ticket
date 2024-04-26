package woowacourse.movie.presentation.contract

interface MovieDetailContract {
    interface View {
        fun showMovieDetail(
            posterImageId: Int,
            title: String,
            screeningStartDate: String,
            runningTime: Int,
            summary: String,
        )

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
