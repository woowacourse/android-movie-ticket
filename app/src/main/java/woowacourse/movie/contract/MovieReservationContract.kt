package woowacourse.movie.contract

interface MovieReservationContract {
    interface View {
        fun showCurrentResultTicketCountView(info: Int)

        fun setMovieView()

        fun setOnButtonsClickListener()

        fun ticketing()
    }

    interface Presenter {
        fun clickMinusNumberButton()

        fun clickPlusNumberButton()

        fun setMovieInfo()

        fun setCurrentResultTicketCountInfo()

        fun setClickListener()
    }
}
