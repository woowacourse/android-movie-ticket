package woowacourse.movie.activity.seatselection

import android.widget.TextView

class SeatSelectionPresenter : SeatSelectionContract.Presenter {
    private var view: SeatSelectionContract.View? = null

    override fun attachView(view: SeatSelectionContract.View) {
        this.view = view
    }

    override fun onSeatClicked(seat: TextView) {
        view?.showSelectedSeat(seat)
    }
}
