package woowacourse.movie.view.reservation

interface SeatSelectContract {
    interface View {
        fun showReservationDialog(
            title: String,
            message: String,
        )
    }

    interface Presenter
}
