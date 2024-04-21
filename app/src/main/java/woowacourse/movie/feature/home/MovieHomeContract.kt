package woowacourse.movie.feature.home

import woowacourse.movie.base.BasePresenter

interface MovieHomeContract {
    interface View {
        fun setUpMovieContentListAdapter()
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContents()
    }
}
