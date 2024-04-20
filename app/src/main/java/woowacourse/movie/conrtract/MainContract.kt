package woowacourse.movie.conrtract

import woowacourse.movie.presenter.BasePresenter

interface MainContract {
    interface View {
        fun setUpMovieContentListAdapter()
    }

    interface Presenter : BasePresenter {
        fun saveMovieContent()
    }
}
