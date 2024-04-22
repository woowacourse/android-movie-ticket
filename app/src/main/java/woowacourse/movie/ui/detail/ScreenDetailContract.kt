package woowacourse.movie.ui.detail

import woowacourse.movie.ui.ScreenDetailUI

interface ScreenDetailContract {
    interface View {
        fun showScreen(screen: ScreenDetailUI)

        fun showTicket(count: Int)

        fun navigateToReservation(navigationId: Int)

        fun showToastMessage(e: Throwable)

        fun showSnackBar(e: Throwable)

        fun goToBack(e: Throwable)

        fun unexpectedFinish(e: Throwable)
    }

    interface Presenter {
        fun loadScreen(screenId: Int)

        fun plusTicket()

        fun minusTicket()

        fun reserve(screenId: Int)
    }
}
