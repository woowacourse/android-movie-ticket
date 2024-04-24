package woowacourse.movie.feature.home

import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BasePresenter

interface MovieHomeContract {
    interface View {
        fun setUpMovieList(movies: List<Movie>)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData()
    }
}
