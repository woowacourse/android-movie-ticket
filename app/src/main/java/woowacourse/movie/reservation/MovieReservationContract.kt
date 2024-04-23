package woowacourse.movie.reservation

import woowacourse.movie.list.Movie

interface MovieReservationContract {
    interface View {
        val presenter: MovieReservationPresenter

        fun showCurrentResultTicketCountView(info: Int)

        fun setMovieView(info: Movie)

        fun setOnPlusButtonClickListener()

        fun setOnMinusButtonClickListener()

        fun setOnTicketingButtonListener()

        fun startMovieTicketActivity(info: Int)
    }

    interface Presenter {
        fun setCurrentResultTicketCountInfo()

        fun setPlusButtonClickInfo()

        fun setMinusButtonClickInfo()

        fun setTicketingButtonClickInfo()

        fun storeMovieData(movieData: Movie?)

        fun setMovieInfo()
    }
}
