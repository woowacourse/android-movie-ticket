package woowacourse.movie.screen.reservation

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation

interface ReservationContract {
    interface View {
        fun readMovieData(): Movie?

        fun setupReservationCompletedButton(movie: Movie)

        fun setupTicketQuantityControls(quantity: Quantity)

        fun setQuantityText(newText: String)

        fun initializeMovieDetails(movie: Movie)

        fun moveToCompletedActivity(reservation: Reservation)
    }

    interface Presenter {
        fun onStart()

        fun onReservationCompleted(movie: Movie)

        fun plus()

        fun minus()
    }
}
