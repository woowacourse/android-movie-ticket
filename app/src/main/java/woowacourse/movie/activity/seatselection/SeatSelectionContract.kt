package woowacourse.movie.activity.seatselection

import android.widget.TextView
import woowacourse.movie.domain.Ticket

interface SeatSelectionContract {
    interface View {
        fun showMovieInfo(ticket: Ticket)
    }

    interface Presenter {
        fun attachView(view: View)

        fun loadMovie(ticket: Ticket)

        fun onSeatClicked(seat: TextView)
    }
}
