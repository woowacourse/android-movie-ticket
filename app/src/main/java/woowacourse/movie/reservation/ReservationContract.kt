package woowacourse.movie.reservation

import android.content.Intent
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

interface ReservationContract {
    interface View {
        fun setupReservationCompletedButton(movie: Movie)

        fun setQuantityText(newText: String)

        fun initializeMovieDetails(movie: Movie)

        fun moveToCompletedActivity(ticket: Ticket)
    }

    interface Presenter {
        fun onViewCreated(intent: Intent)

        fun getMovieDate(intent: Intent): Movie?

        fun onClicked(movie: Movie)

        fun increaseQuantity()

        fun decreaseQuantity()
    }
}
