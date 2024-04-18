package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.Screen

interface ScreenContract {
    interface View {
        fun showScreens(screens: List<Screen>)
    }

    interface Presenter {
        fun loadScreens()
    }
}