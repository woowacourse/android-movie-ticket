package woowacourse.movie.presentation.contract

import woowacourse.movie.domain.model.Movie

interface MainContract {
    interface View {
        fun showMovieList(movieList: List<Movie>)
        fun moveToMovieDetail(movie: Movie)
    }
    
    interface Presenter {
        fun loadMovieList()
        fun requestMovieDetail(movie: Movie)
    }
}
