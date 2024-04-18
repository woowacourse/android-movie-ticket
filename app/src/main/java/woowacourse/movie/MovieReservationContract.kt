package woowacourse.movie

interface MovieReservationContract {
    interface View {
        fun showCurrentResultTicketCountView()
    }

    interface Presenter {
        fun clickMinusNumberButton()

        fun clickPlusNumberButton()
    }
}
