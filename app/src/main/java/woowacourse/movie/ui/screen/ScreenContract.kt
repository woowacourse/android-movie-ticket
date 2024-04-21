package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.ui.ScreenPreviewUI

interface ScreenContract {
    interface View {
        fun showScreens(screens: List<Screen>)
    }

    interface Presenter {
        fun loadScreens()
    }
}

interface ScreenContract2 {
    interface View {
        fun showScreens(screens: List<ScreenPreviewUI>)
    }

    interface Presenter {
        fun loadScreens()
    }
}
