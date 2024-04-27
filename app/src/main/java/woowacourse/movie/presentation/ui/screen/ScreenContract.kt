package woowacourse.movie.presentation.ui.screen

import woowacourse.movie.domain.model.ScreenView
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView

interface ScreenContract {
    interface View : BaseView {
        fun showScreens(screens: List<ScreenView>)

        fun navigateToDetail(id: Int)
    }

    interface Presenter : BasePresenter, ScreenActionHandler {
        fun loadScreens()
    }
}
