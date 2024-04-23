package woowacourse.movie.home

import woowacourse.movie.db.Movies
import woowacourse.movie.model.Movie

class HomePresenter(
    private val contract: HomeContract.View,
) : HomeContract.Presenter {
    private val movies = Movies.obtainMovies()

    override fun obtainMovies(): List<Movie> = movies.toList()

    override fun deliverMovie(movieId: Int) {
        contract.moveToReservationDetail(movieId)
    }
}
