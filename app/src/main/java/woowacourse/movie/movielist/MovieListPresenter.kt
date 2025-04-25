package woowacourse.movie.movielist

import woowacourse.movie.data.MovieFactory

class MovieListPresenter(private val view: MovieListContract.View): MovieListContract.Presenter {
    override fun updateMovies() {
        val movies = MovieFactory().getAll()
        view.showMovieList(movies)
    }
}