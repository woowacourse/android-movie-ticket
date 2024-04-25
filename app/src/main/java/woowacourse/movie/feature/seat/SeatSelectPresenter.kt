package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.toSeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.toSeatSelectTableUiModels
import woowacourse.movie.model.Seats
import woowacourse.movie.model.data.MovieRepository

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieRepository: MovieRepository,
) :
    SeatSelectContract.Presenter {
    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        val movieUiModel = movie.toSeatSelectMovieUiModel()
        view.initializeMovie(movieUiModel)
    }

    override fun initializeSeatTable(
        row: Int,
        col: Int,
    ) {
        val seats = Seats(row, col)
        val seatsUiModel = seats.toSeatSelectTableUiModels()
        view.initializeSeatTable(seatsUiModel)
    }
}
