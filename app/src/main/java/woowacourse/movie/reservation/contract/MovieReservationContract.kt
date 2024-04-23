package woowacourse.movie.reservation.contract

import woowacourse.movie.list.model.Movie
import woowacourse.movie.reservation.presenter.MovieReservationPresenter

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
