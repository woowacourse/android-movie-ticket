package woowacourse.movie.seat

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.booking.detail.TicketUiModel

interface SeatSelectionContract {
    interface View {
        fun showTicket(ticket: TicketUiModel)

        fun showSeatState(
            seat: TextView,
            isSelected: Boolean,
        )

        fun showToastErrorAndFinish(message: String)

        fun setButtonEnabled(enabled: Boolean)

        fun showBookingAlertDialog(result: TicketUiModel)

        fun getContext(): Context
    }

    interface Presenter {
        fun initializeData(savedInstanceState: Bundle?)

        fun onSeatClicked(seat: TextView)

        fun onButtonClicked()
    }
}
