package woowacourse.movie.contract

import woowacourse.movie.presenter.MovieReservationPresenter

interface MovieReservationContract {
    interface View {
        val presenter: MovieReservationPresenter

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
