package woowacourse.movie.reservation.detail

import woowacourse.movie.model.Movie

interface ReservationDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun updateCount(ticketCount: Int)

        fun initializeReservationButton(
            movieId: Int,
            ticketCount: Int,
        )

        fun showErrorToast()
    }

    interface Presenter {
        fun increaseCount()

        fun decreaseCount()

        fun deliverMovie()

        fun deliverReservationHistory()
    }
}
