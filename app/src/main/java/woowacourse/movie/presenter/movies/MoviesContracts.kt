package woowacourse.movie.presenter.movies

import woowacourse.movie.model.movie.Movie

interface MoviesContracts {
    interface View {
        fun showMovies(movies: List<Movie>)

        fun showReservationView(movie: Movie)

        fun showAdvertisement(url: String)
    }

    interface Presenter {
        fun initView()

        fun onReservationRequested(id: Long)

        fun onAdvertisementRequested(url: String)
    }
}
