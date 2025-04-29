package woowacourse.movie.activity.booking

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.ui.MovieUiModel

interface BookingContract {
    interface View {
        fun setupPage(movieUiModel: MovieUiModel)

        fun moveToSeatSelection(ticket: Ticket)

        fun showTicketCount(count: Int)

        fun updateDateSpinner(
            dates: List<String>,
            selectedPosition: Int,
        )

        fun updateTimeSpinner(
            times: List<String>,
            selectedPosition: Int,
        )
    }

    interface Presenter {
        fun initData(movie: Movie)

        fun getSelectedDate(): String

        fun getSelectedTime(): String

        fun confirmBooking()

        fun decreaseTicketCount()

        fun increaseTicketCount()

        fun selectDate(date: String)

        fun selectTime(time: String)

        fun saveState(state: Bundle)

        fun restoreState(state: Bundle)
    }
}
