package woowacourse.movie.reservation

import android.content.Intent
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.UiMovie

interface ReservationContract {
    interface View {
        fun setupReservationCompletedButton(uiMovie: UiMovie)

        fun setQuantityText(newText: String)

        fun initializeMovieDetails(uiMovie: UiMovie)

        fun moveToCompletedActivity(ticket: Ticket)
    }

    interface Presenter {
        fun onViewCreated(intent: Intent)

        fun getMovieDate(intent: Intent): UiMovie?

        fun onClicked(uiMovie: UiMovie)

        fun increaseQuantity()

        fun decreaseQuantity()
    }
}
