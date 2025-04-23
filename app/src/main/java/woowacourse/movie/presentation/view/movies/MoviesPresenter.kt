package woowacourse.movie.presentation.view.movies

import woowacourse.movie.presentation.fixture.dummyMovie

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun fetchData() {
        val movies = listOf(dummyMovie)
        view.setScreen(movies) { movie ->
            view.navigateToReservationScreen(movie)
        }
    }
}
