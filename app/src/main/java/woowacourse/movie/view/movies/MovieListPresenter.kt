package woowacourse.movie.view.movies

class MovieListPresenter(
    private val view: MovieListContract.View,
    private val model: MovieListContract.MovieModel,
) : MovieListContract.Presenter {
    override fun setMovies() = view.showMovieList(model)

    override fun onSelectMovie(movieIdx: Int) = view.moveToBookingComplete(movieIdx)
}
