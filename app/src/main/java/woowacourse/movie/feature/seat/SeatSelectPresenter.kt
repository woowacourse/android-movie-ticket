package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.ReservationAmount
import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatRating
import woowacourse.movie.model.Seats
import woowacourse.movie.model.SelectedSeats
import woowacourse.movie.model.data.MovieRepository

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    reservationCountValue: Int,
    private val movieRepository: MovieRepository,
) :
    SeatSelectContract.Presenter {
    private lateinit var seats: Seats
    private val selectedSeats = SelectedSeats(ReservationCount(reservationCountValue))
    private var reservationAmount = ReservationAmount(0)

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        val movieUiModel = SeatSelectMovieUiModel.from(movie)
        view.initializeMovie(movieUiModel)
    }

    override fun initializeSeatTable(
        row: Int,
        col: Int,
    ) {
        seats = Seats(row, col)
        val seatsUiModel = SeatSelectTableUiModel.from(seats)
        view.initializeSeatTable(seatsUiModel)
    }

    override fun selectSeat(
        row: Int,
        col: Int,
    ) {
        val seat = seats.table[row][col]
        if (seat !in selectedSeats) {
            applySelectSeat(seat, row, col)
        } else {
            applyUnselectSeat(seat, row, col)
        }

        view.updateReservationAmount(reservationAmount.amount)
    }

    private fun applySelectSeat(
        seat: Seat,
        row: Int,
        col: Int,
    ) {
        if (!selectedSeats.isSelectable()) {
            view.showCannotSelectSeat()
            return
        }

        selectedSeats.add(seat)
        reservationAmount += SeatRating.from(seat).amount
        view.selectSeat(row, col, selectedSeats.isConfirm())
    }

    private fun applyUnselectSeat(
        seat: Seat,
        row: Int,
        col: Int,
    ) {
        selectedSeats.remove(seat)
        reservationAmount -= SeatRating.from(seat).amount
        view.unselectSeat(row, col)
    }

    override fun confirmSeatSelection() {
        view.moveReservationCompleteView(selectedSeats)
    }
}
