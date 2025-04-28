package woowacourse.movie.activity.seatselection

import android.widget.TextView
import woowacourse.movie.domain.Ticket

interface SeatSelectionContract {
    interface View {
        fun showMovieInfo(ticket: Ticket)

        fun showMoney(money: Int)

        fun updateConfirmButtonState(hasSelection: Boolean)

        fun moveToBookingResult(ticket: Ticket)
    }

    interface Presenter {
        fun loadMovie(ticket: Ticket)

        fun calculateMoney(
            rowIndex: Int,
            isSelected: Boolean,
        )

        fun calculateAudienceCount(isSelected: Boolean): Int

        fun selectSeat(
            seat: TextView,
            ticket: Ticket,
        ): Boolean

        fun handleConfirmButtonActivation(seats: Sequence<Sequence<TextView>>)

        fun confirmSeatSelection(
            seats: Sequence<Sequence<TextView>>,
            ticket: Ticket,
        )
    }
}
