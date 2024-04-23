package woowacourse.movie.base

import woowacourse.movie.utils.MovieErrorCode

interface BaseContract {
    interface View {
        fun onError(errorCode: MovieErrorCode)
    }

    interface Presenter
}
