package woowacourse.movie.contract

interface MovieReservationContract {
    interface View {
        fun showCurrentResultTicketCountView(info: Int)

        fun setMovieView()

        fun setOnPlusButtonClickListener()

        fun setOnMinusButtonClickListener()

        fun setOnTicketingButtonListener()

        fun startMovieTicketActivity(info: Int)
    }

    interface Presenter {
        fun setCurrentResultTicketCountInfo()

        fun setMovieInfo()

        fun setPlusButtonClickInfo()

        fun setMinusButtonClickInfo()

        fun setTicketingButtonClickInfo()
    }
}
