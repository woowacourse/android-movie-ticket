package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.Movie

interface MovieReservationContract {
    interface View {
        fun showMovie(movie: Movie)

        fun showCurrentResultTicketCountView()
    }

    interface Presenter {
        fun loadMovie()

        fun clickMinusNumberButton()

        fun clickPlusNumberButton()
    }
}
