package woowacourse.movie.ui.screen

import woowacourse.movie.ui.ScreenPreviewUI

interface ScreenContract {
    interface View {
        fun showScreens(screens: List<ScreenPreviewUI>)
    }

    interface Presenter {
        fun loadScreens()
    }
}
