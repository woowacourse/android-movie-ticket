package woowacourse.movie.presentation.reservation.result

import android.content.Intent
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket

interface ReservationResultContract {
    interface View {
        fun showMovieInformation(title: String)

        fun showReservationInformation(
            ticket: Ticket,
            seats: Seats,
        )
    }

    interface Presenter {
        fun fetch(intent: Intent)
    }
}
