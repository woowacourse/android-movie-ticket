package woowacourse.movie.reservation

import woowacourse.movie.model.Movie

interface ReservationContract {
    interface View {
        fun readMovieData(): Movie?

        fun setupReservationCompletedButton(movie: Movie)

        fun setupTicketQuantityControls()

        fun setQuantityText(newText: String)

        fun initializeMovieDetails(movie: Movie)

        fun moveToCompletedActivity(
            movie: Movie,
            quantity: Int,
        )
    }

    interface Presenter {
        fun onViewCreated()

        fun onClicked(movie: Movie)

        fun plus()

        fun minus()
    }
}
