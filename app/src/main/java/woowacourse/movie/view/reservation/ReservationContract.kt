package woowacourse.movie.view.reservation

import android.content.Intent
import woowacourse.movie.model.MovieTicket
import java.time.LocalDate

interface ReservationContract {
    interface View {
        fun updateMovieInfo(
            posterResId: Int,
            title: String,
            startDate: String,
            endDate: String,
            runningTime: Int,
        )

        fun showErrorDialog()

        fun setTicketCount(count: Int)

        fun showReservationDialog(
            title: String,
            message: String,
        )
    }

    interface Presenter {
        fun fetchData(intent: Intent)

        fun plusTicketCount()

        fun minusTicketCount()

        fun onReservationCompleted(
            title: String,
            message: String,
        )
    }
}
