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

        fun updateDateAdapter(
            duration: List<LocalDate>,
            selected: Int,
        )

        fun updateTimeAdapter(times: List<String>)
    }

    interface Presenter {
        fun fetchData(intent: Intent)

        fun initDateAdapter()

        fun plusTicketCount()

        fun minusTicketCount()

        fun onDateSelected(date: LocalDate, position: Int)

        fun onTimeSelected(index: Int)

        fun onReservationCompleted(
            title: String,
            message: String,
        )

        fun createTicket(): MovieTicket
    }
}
