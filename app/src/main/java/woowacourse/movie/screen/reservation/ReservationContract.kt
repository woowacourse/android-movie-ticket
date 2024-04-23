package woowacourse.movie.screen.reservation

import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation
import woowacourse.movie.screen.main.MovieModel

interface ReservationContract {
    interface View {
        fun readMovieData(): Long?

        fun setupReservationCompletedButton()

        fun setupTicketQuantityControls(quantity: Quantity)

        fun setQuantityText(newText: String)

        fun initializeMovieDetails(movie: MovieModel)

        fun moveToCompletedActivity(reservation: Reservation)
    }

    interface Presenter {
        fun onStart()

        fun onReservationCompleted()

        fun plus()

        fun minus()
    }
}
