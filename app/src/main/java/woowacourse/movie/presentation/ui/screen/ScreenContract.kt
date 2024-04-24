package woowacourse.movie.presentation.ui.screen

import woowacourse.movie.domain.model.ScreenViewType
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView

interface ScreenContract {
    interface View : BaseView {
        fun showScreens(screens: List<ScreenViewType>)

        fun navigateToDetail(id: Int)
    }

    interface Presenter : BasePresenter, ScreenActionHandler {
        fun loadScreens()
    }
}
