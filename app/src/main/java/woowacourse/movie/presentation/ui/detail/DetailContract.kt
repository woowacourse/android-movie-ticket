package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView

interface DetailContract {
    interface View : BaseView {
        fun showScreen(screen: Screen)

        fun showTicket(count: Int)

        fun navigateToReservation(id: Int)

        fun back()
    }

    interface Presenter : BasePresenter {
        fun loadScreen(id: Int)

        fun plusTicket()

        fun minusTicket()

        fun reserve()
    }
}
