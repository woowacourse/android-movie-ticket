package woowacourse.movie.presentation.ui.reservation

interface ReservationResultContract {
    interface View {
        fun showTitle(title: String)

        fun showScreeningDate(screeningDate: String)

        fun showReservationCount(reservationCount: Int)

        fun showTotalPrice(totalPrice: Int)

        fun showError(error: String)
    }

    interface Presenter {
        fun loadReservationDetails()
    }
}
