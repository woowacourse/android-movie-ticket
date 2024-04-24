package woowacourse.movie.reservation.detail

import woowacourse.movie.model.Movie

interface ReservationDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun updateCount(ticketCount: Int)

        fun showErrorToast()

        fun moveToReservationFinished(
            movieId: Int,
            ticketCount: Int,
        )
    }

    interface Presenter {
        fun increaseCount()

        fun decreaseCount()

        fun loadMovie()

        fun deliverReservationInformation()
    }
}
