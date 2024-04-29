package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.ScreenAd

interface ScreenContract {
    interface View {
        fun showScreens(screens: List<ScreenAd>)
    }

    interface Presenter {
        fun loadScreen()
    }
}
