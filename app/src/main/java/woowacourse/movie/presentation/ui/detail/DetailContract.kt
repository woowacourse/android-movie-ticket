package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView
import woowacourse.movie.presentation.model.ReservationInfo

interface DetailContract {
    interface View : BaseView {
        fun showScreen(screen: Screen)

        fun showTicket(count: Int)

        fun navigateToSeatSelection(reservationInfo: ReservationInfo)

        fun back()
    }

    interface Presenter : BasePresenter {
        fun loadScreen(id: Int)

        fun registerDate(date: String)

        fun registerTime(time: String)

        fun plusTicket()

        fun minusTicket()

        fun selectSeat()
    }
}
