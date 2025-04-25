package woowacourse.movie.activity.seatselection

import android.widget.TextView
import woowacourse.movie.domain.Ticket

interface SeatSelectionContract {
    interface View {
        fun showMovieInfo(ticket: Ticket)

        fun showMoney(money: Int)

        fun updateConfirmButtonState(hasSelection: Boolean)
    }

    interface Presenter {
        fun attachView(view: View)

        fun loadMovie(ticket: Ticket)

        fun calculateMoney(
            rowIndex: Int,
            isSelected: Boolean,
        )

        fun onSeatClicked(seat: TextView): Boolean

        fun handleConfirmButtonActivation(seats: Sequence<Sequence<TextView>>)
    }
}
