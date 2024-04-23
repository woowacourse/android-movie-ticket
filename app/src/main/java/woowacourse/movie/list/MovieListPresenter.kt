package woowacourse.movie.list

class MovieListPresenter(
    val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override val movieList = MovieDataSource.movieList

    override fun setMoviesInfo() {
        view.showMoviesInfo(movieList)
    }

    override fun setListViewClickListenerInfo() {
        view.setOnListViewClickListener(movieList)
    }
}
