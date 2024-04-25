package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.toSeatSelectTableUiModels
import woowacourse.movie.model.Seats

class SeatSelectPresenter(private val view: SeatSelectContract.View) :
    SeatSelectContract.Presenter {
    override fun initializeSeatTable(row: Int, col: Int) {
        val seats = Seats(row, col)
        val seatsUiModel = seats.toSeatSelectTableUiModels()
        view.initializeSeatTable(seatsUiModel)
    }
}
