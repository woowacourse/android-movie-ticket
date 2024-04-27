package woowacourse.movie.seats.presenter

import woowacourse.movie.common_data.MovieDataSource
import woowacourse.movie.seats.contract.SeatsContract
import woowacourse.movie.seats.model.Seat
import woowacourse.movie.seats.model.SeatsDataSource
import woowacourse.movie.seats.model.SeatsDataSource.seatTotalPrice

class SeatsPresenter(val view: SeatsContract.View) : SeatsContract.Presenter {
    override lateinit var seat: Seat

    override fun createSeat(
        rowIndex: Int,
        colIndex: Int,
    ) {
        seat = Seat.of(rowIndex, colIndex)
    }

    override fun setPriceInfo() {
        view.setTotalPrice(seatTotalPrice)
    }

    override fun storeMovieId(movieId: Long) {
        SeatsDataSource.movieId = movieId
    }

    override fun setMovieTitleInfo() {
        val id = SeatsDataSource.movieId.toInt()
        view.setMovieTitle(MovieDataSource.movieList[id].title)
    }

    override fun setSeatsCellsBackgroundColorInfo() {
        seat.select()
        view.setSeatCellBackgroundColor(seat)
        view.setTotalPrice(seatTotalPrice)
    }

    override fun setSeatsTextInfo() {
        view.setSeatsText(seat)
    }

    override fun setSeatsViewInfo() {
        view.initSeatsView(seat)
    }
}
