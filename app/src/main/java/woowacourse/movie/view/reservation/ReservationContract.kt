package woowacourse.movie.view.reservation

interface ReservationContract {
    interface View {
        fun setTicketCount(count: Int)
    }

    interface Presenter {
        fun plusTicketCount()

        fun minusTicketCount()
    }
}
