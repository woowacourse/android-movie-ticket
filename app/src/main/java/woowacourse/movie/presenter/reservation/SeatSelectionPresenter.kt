package woowacourse.movie.presenter.reservation

import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.db.SeatsDao
import woowacourse.movie.model.Movie

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val seatsDao: SeatsDao,
    private val screeningDao: ScreeningDao,
) : SeatSelectionContract.Presenter {
    override fun loadSeatNumber() {
        val seats = seatsDao.findAll()
        seats.forEachIndexed { index, seat ->
            view.showSeatNumber(index, seat)
        }
    }

    override fun loadMovie(movieId: Int) {
        val movie: Movie = screeningDao.find(movieId)
        view.showMovieTitle(movie)
    }
}
