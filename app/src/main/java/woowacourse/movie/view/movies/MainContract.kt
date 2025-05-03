package woowacourse.movie.view.movies

import woowacourse.movie.domain.MovieItem

interface MainContract {
    interface Presenter {
        fun fetchData()
    }

    interface View {
        fun showMoviesScreen(movieItems: List<MovieItem>)
    }
}
