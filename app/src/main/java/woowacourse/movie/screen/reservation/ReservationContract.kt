package woowacourse.movie.screen.reservation

import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation
import woowacourse.movie.screen.main.MovieModel

interface ReservationContract {
    interface View {
        fun initializeMovieDetails(movie: MovieModel)

        fun setupReservationCompleteControls()

        fun setupTicketQuantityControls(quantity: Quantity)

        fun updateTicketQuantity(newText: String)

        fun navigateToCompleteScreen(reservation: Reservation)
    }

    interface Presenter {
        fun fetchMovieDetails(id: Long)

        fun completeReservation()

        fun increaseTicketQuantity()

        fun decreaseTicketQuantity()
    }
}
