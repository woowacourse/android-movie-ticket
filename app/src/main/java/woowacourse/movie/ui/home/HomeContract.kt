package woowacourse.movie.ui.home

import woowacourse.movie.domain.movie.ScreenView
import woowacourse.movie.repository.ScreenListRepository

interface HomeContract {
    interface View {
        fun startReservationActivity(screenId: Long)
        fun showScreens(screenListRepository: ScreenListRepository)
    }

    interface Presenter {
        fun onScreenSelected(screenId: Long)
        fun loadScreens(screenListRepository: ScreenListRepository)
    }
}