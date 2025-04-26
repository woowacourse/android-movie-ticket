package woowacourse.movie.movielist

import woowacourse.movie.data.MovieFactory

class MovieListPresenter(private val view: MovieListContract.View) : MovieListContract.Presenter {
    private val movies = MovieFactory().getAll()

    override fun updateMovies() {
        view.showMovieList(movies)
    }
}
