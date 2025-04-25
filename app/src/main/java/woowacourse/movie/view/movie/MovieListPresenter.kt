package woowacourse.movie.view.movie

import woowacourse.movie.domain.model.Movie

class MovieListPresenter(
    val movieListView: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun getMovieList(): List<Movie> = Movie.DUMMY_MOVIES

    override fun updateMovieList() {
        val movies = getMovieList()
        movieListView.setMoveList(movies)
    }
}
