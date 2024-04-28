package woowacourse.movie.ui.home

import woowacourse.movie.domain.movie.ScreenView

interface HomeContract {
    interface View

    interface Presenter {
        fun loadList(): List<ScreenView>
    }
}
