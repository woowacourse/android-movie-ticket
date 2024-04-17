package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket

interface MovieDetailContract {
    interface View {
        fun onCountUpdate(count: Int)

        fun onReservationComplete(movieTicket: MovieTicket)
    }

    interface Presenter {
        fun plusReservationCount()

        fun minusReservationCount()

        fun reservation(movie: Movie)
    }
}
