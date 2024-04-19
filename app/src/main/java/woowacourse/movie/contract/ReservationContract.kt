package woowacourse.movie.contract

interface ReservationContract {
    interface View {
        fun updateTicketCount()
    }

    interface Presenter {
        fun subTicketCount()

        fun addTicketCount()
    }
}
