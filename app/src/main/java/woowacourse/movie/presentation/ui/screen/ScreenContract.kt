package woowacourse.movie.presentation.ui.screen

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView

interface ScreenContract {
    interface View : BaseView {
        fun showScreens(screens: List<Screen>)
    }

    interface Presenter : BasePresenter {
        fun loadScreens()
    }
}
