package woowacourse.movie.presenter.reservation

import woowacourse.movie.db.SeatsDao

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val dao: SeatsDao,
) : SeatSelectionContract.Presenter {
    override fun loadSeatNumber() {
        val seats = dao.findAll()
        seats.forEachIndexed { index, seat ->
            view.showSeatNumber(index, seat)
        }
    }
}
