package woowacourse.movie.presenter.contract

import woowacourse.movie.adapter.MovieAdapter

interface MovieListContract {
    interface View {
        fun navigateToTicketing(movieId: Long)
    }

    interface Presenter {
        fun getAdapter(): MovieAdapter
    }
}
