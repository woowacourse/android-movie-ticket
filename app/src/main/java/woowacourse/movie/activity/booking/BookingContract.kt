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
        fun attachView(view: View)

        fun initData(movie: Movie)

        fun getSelectedDate(): Int

        fun getSelectedTime(): Int

        fun onConfirmButtonClicked()

        fun onMinusButtonClicked()

        fun onPlusButtonClicked()

        fun onDateSelected(position: Int)

        fun onTimeSelected(position: Int)

        fun onSaveState(state: Bundle)

        fun onRestoreState(state: Bundle)
    }
}
