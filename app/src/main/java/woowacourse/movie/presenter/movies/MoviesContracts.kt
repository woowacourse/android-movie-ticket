package woowacourse.movie.presenter.movies

import woowacourse.movie.model.movie.Movie

interface MoviesContracts {
    interface View {
        fun showMovies(movies: List<Movie>)

        fun showReservationView(movie: Movie)
    }

    interface Presenter {
        fun initView()

        fun createMovie(id: Long)
    }
}
