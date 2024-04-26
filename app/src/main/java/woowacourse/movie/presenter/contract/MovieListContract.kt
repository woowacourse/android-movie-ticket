package woowacourse.movie.presenter.contract

import woowacourse.movie.adapter.ScreeningAdapter

interface MovieListContract {
    interface View {
        fun navigateToTicketing(screeningId: Long)
    }

    interface Presenter {
        fun getAdapter(): ScreeningAdapter
    }
}
