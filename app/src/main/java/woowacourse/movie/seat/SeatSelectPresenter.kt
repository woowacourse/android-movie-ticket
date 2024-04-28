package woowacourse.movie.seat

import woowacourse.movie.db.Movies
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieId: Int,
    private val ticket: Ticket,
) : SeatSelectContract.Presenter {
    override fun loadMovieTitle() {
        val title = Movies.obtainMovie(movieId).title

        view.showMovieTitle(title)
    }

    override fun selectSeat() {
        TODO("Not yet implemented")
    }

    override fun unselectSeat() {
        TODO("Not yet implemented")
    }
}
