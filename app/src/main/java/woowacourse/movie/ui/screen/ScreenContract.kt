package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.ui.ScreenPreviewUI

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
