package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.Movie

interface MovieReservationContract {
    interface View {
        fun showMovie(movie: Movie)

        fun showCurrentResultTicketCountView()
    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun clickMinusNumberButton()

        fun clickPlusNumberButton()
    }
}
