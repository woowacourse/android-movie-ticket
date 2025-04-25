package woowacourse.movie.main

interface MainContract {
    interface View {
        fun showMovies(movies: List<Item>)
    }

    interface Presenter {
        fun initMovies()
    }
}
