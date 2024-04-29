package woowacourse.movie.ui.screen

import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.screen.adapter.ScreenAd

interface ScreenContract {
    interface View {
        fun showScreens(screens: List<ScreenPreviewUI>)

        fun showScreens2(screens: List<ScreenAd>)
    }

    interface Presenter {
        fun loadScreens()

        fun loadScreen2()
    }
}
