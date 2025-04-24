package woowacourse.movie.view.movie

import woowacourse.movie.domain.model.Movie

class MovieListPresenter(
    val movieListView: MovieListContract.View
) : MovieListContract.Presenter {
    override fun getMovieList(): List<Movie> {
        return listOf(
            Movie.DUMMY_MOVIE
        )
    }

    override fun updateMovieList() {
        val movies = getMovieList()
        movieListView.setMoveList(movies)
    }
}