package woowacourse.movie.reservation

import woowacourse.movie.model.Movie

interface ReservationFinishedContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun showReservationHistory(
            ticketCount: Int,
            price: Int,
        )
    }

    interface Presenter {
        fun loadMovieInformation()

        fun loadReservationInformation()
    }
}
