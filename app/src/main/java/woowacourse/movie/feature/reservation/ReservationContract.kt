package woowacourse.movie.feature.reservation

import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.feature.main.ui.MovieModel

interface ReservationContract {
    interface View {
        fun initializeMovieDetails(movie: MovieModel)

        fun setupReservationCompleteControls()

        fun setupTicketQuantityControls(quantity: Quantity)

        fun updateTicketQuantity(newText: String)

        fun navigateToCompleteScreen(id: Long)
    }

    interface Presenter {
        fun fetchMovieDetails(id: Long)

        fun completeReservation()

        fun increaseTicketQuantity()

        fun decreaseTicketQuantity()
    }
}
