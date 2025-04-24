package woowacourse.movie.activity.seatselection

import android.widget.TextView
import woowacourse.movie.domain.Ticket

class SeatSelectionPresenter : SeatSelectionContract.Presenter {
    private var view: SeatSelectionContract.View? = null

    override fun attachView(view: SeatSelectionContract.View) {
        this.view = view
    }

    override fun loadMovie(ticket: Ticket) {
        view?.showMovieInfo(ticket)
    }

    override fun onSeatClicked(seat: TextView) {
        seat.isSelected = !seat.isSelected
    }
}
