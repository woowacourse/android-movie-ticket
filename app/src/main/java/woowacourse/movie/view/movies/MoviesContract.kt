package woowacourse.movie.view.movies

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)
    }

    interface Presenter {
        fun loadData()
    }
}
