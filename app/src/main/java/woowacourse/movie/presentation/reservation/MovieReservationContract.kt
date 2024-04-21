package woowacourse.movie.presentation.reservation

interface MovieReservationContract {
    interface View {
        fun showCurrentResultTicketCountView()
    }

    interface Presenter {
        fun clickMinusNumberButton()

        fun clickPlusNumberButton()
    }
}
