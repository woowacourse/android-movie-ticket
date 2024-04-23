package woowacourse.movie.home

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Movies

class ReservationHomePresenter(
    private val contract: ReservationHomeContract.View,
) : ReservationHomeContract.Presenter {
    private val movies = Movies.obtainMovies()

    override fun obtainMovies(): List<Movie> = movies.toList()

    override fun deliverMovie(movieId: Int) {
        contract.moveToReservationDetail(movieId)
    }
}
