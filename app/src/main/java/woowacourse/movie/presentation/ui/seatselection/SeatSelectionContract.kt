package woowacourse.movie.presentation.ui.seatselection

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView
import woowacourse.movie.presentation.model.ReservationInfo

interface SeatSelectionContract {
    interface View : BaseView {
        fun showScreen(screen: Screen)

        fun showSeatBoard(seats: List<Seat>)

        fun initClickListener(
            ticketCount: Int,
            seats: List<Seat>,
        )
    }

    interface Presenter : BasePresenter {
        fun updateUiModel(reservationInfo: ReservationInfo)

        fun loadScreen(id: Int)

        fun loadSeatBoard(id: Int)
    }
}
