package woowacourse.movie.reservation.finished

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
        fun deliverMovieInformation()

        fun deliverReservationInformation()
    }
}
