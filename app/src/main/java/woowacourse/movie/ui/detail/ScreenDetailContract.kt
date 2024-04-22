package woowacourse.movie.ui.detail

import woowacourse.movie.ui.ScreenDetailUI

interface ScreenDetailContract {
    interface View {
        fun showScreen(screen: ScreenDetailUI)

        fun showTicket(count: Int)

        fun navigateToReservation(navigationId: Int)

        fun showToastMessage(message: String)

        fun showSnackBar(message: String)

        fun goToBack(message: String)

        fun unexpectedFinish(message: String)
    }

    interface Presenter {
        fun loadScreen(screenId: Int)

        fun plusTicket()

        fun minusTicket()

        fun reserve(screenId: Int)
    }
}
