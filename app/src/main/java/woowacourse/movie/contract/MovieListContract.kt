package woowacourse.movie.contract

import woowacourse.movie.model.theater.Theater

interface MovieListContract {
    interface View {
        fun navigateToMovieDetail(theater: Theater)
    }

    interface Presenter {
        fun onItemButtonClicked(position: Int)
    }
}