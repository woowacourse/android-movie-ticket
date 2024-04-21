package woowacourse.movie.feature.home

import woowacourse.movie.base.BasePresenter

interface MainContract {
    interface View {
        fun setUpMovieContentListAdapter()
    }

    interface Presenter : BasePresenter
}
