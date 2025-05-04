package woowacourse.movie.movielist

class MovieListPresenter(private val view: MovieListContract.View, private val movies: List<FeedItem>) : MovieListContract.Presenter {
    override fun updateMovies() {
        view.showMovieList(movies)
    }
}
