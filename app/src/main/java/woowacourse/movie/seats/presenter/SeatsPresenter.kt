package woowacourse.movie.seats.presenter

import android.widget.TextView
import woowacourse.movie.common.MovieDataSource
import woowacourse.movie.seats.contract.SeatsContract
import woowacourse.movie.seats.model.Seat
import woowacourse.movie.seats.model.SeatsDataSource
import woowacourse.movie.seats.model.SeatsDataSource.date
import woowacourse.movie.seats.model.SeatsDataSource.movieId
import woowacourse.movie.seats.model.SeatsDataSource.seat
import woowacourse.movie.seats.model.SeatsDataSource.seatTotalPrice
import woowacourse.movie.seats.model.SeatsDataSource.selectedSeats
import woowacourse.movie.seats.model.SeatsDataSource.time

class SeatsPresenter(val view: SeatsContract.View) : SeatsContract.Presenter {
    override fun initCell(cell: TextView) {
        view.initCell(cell, seat)
    }

    override fun createSeat(
        rowIndex: Int,
        colIndex: Int,
    ) {
        seat = Seat.of(rowIndex, colIndex)
    }

    override fun selectSeat(
        rowIndex: Int,
        colIndex: Int,
    ) {
        seat = Seat.of(rowIndex, colIndex)
        if (!seat.selected) selectedSeats.add(seat)
        if (seat.selected) selectedSeats.remove(seat)
    }

    override fun startNextActivity() {
        view.startNextActivity(
            movieId,
            MovieDataSource.movieList[movieId.toInt()].title,
            date,
            time,
            selectedSeats,
            seatTotalPrice,
        )
    }

    override fun storeDate(date: String) {
        SeatsDataSource.date = date
    }

    override fun storeTime(time: String) {
        SeatsDataSource.time = time
    }

    override fun setPriceInfo() {
        view.setTotalPrice(seatTotalPrice)
    }

    override fun storeMovieId(movieId: Long) {
        SeatsDataSource.movieId = movieId
    }

    override fun setMovieTitleInfo() {
        val id = movieId.toInt()
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
}
