package woowacourse.movie.presenter.contract

import woowacourse.movie.adapter.MovieAdapter

interface MovieListContract {
    interface View {
        fun navigate(movieId: Int)
    }

    interface Presenter {
        fun getAdapter(): MovieAdapter
    }
}
