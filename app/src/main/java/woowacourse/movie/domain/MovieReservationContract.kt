package woowacourse.movie.domain

interface MovieReservationContract {
    interface View {
        fun showCurrentResultTicketCountView()
    }

    interface Presenter {
        fun clickMinusNumberButton()

        fun clickPlusNumberButton()
    }
}
