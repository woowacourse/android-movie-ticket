package woowacourse.movie.movie

interface MovieListContract {
    interface View {
        fun showMovies(movies: List<Item>)
    }

    interface Presenter {
        fun initMovies()
    }
}
