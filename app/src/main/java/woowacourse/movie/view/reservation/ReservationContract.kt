package woowacourse.movie.view.reservation

interface ReservationContract {
    interface View {
        fun setTicketCount(count: Int)

        fun showReservationDialog(
            title: String,
            message: String,
        )
    }

    interface Presenter {
        fun plusTicketCount()

        fun minusTicketCount()

        fun onReservationCompleted(
            title: String,
            message: String,
        )
    }
}
