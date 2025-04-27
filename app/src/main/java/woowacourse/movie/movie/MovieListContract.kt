package woowacourse.movie.movie

interface MovieListContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)
    }

    interface Presenter {
        fun initMovies()
    }
}
