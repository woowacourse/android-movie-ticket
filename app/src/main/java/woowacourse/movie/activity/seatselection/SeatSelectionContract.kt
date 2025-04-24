package woowacourse.movie.activity.seatselection

import android.widget.TextView

interface SeatSelectionContract {
    interface View {
        fun showSelectedSeat(seat: TextView)
    }

    interface Presenter {
        fun attachView(view: View)

        fun onSeatClicked(seat: TextView)
    }
}
