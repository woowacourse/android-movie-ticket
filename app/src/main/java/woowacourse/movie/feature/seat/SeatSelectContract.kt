package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface SeatSelectContract {
    interface View : ErrorListener {
        fun initializeSeatTable(seats: List<List<SeatSelectTableUiModel>>)
    }

    interface Presenter : BasePresenter {
        fun initializeSeatTable(row: Int, col: Int)
    }
}
