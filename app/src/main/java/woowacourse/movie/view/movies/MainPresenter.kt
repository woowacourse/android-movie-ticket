package woowacourse.movie.view.movies

import woowacourse.movie.domain.Movie

class MainPresenter(
    private val view: MainContract.View,
) : MainContract.Presenter {
    override fun fetchData() {
        val movies: List<Movie> = Movie.dummy
        view.showMoviesScreen(movies) { movie ->
            view.navigateToReservation(movie)
        }
    }
}
