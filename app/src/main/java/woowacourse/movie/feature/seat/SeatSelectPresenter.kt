package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.reservation.ReservationAmount
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.Seats
import woowacourse.movie.model.seat.SelectedSeats

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieRepository: MovieRepository,
) :
    SeatSelectContract.Presenter {
    private lateinit var seats: Seats
    private lateinit var selectedSeats: SelectedSeats
    private var reservationAmount = ReservationAmount()

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        val movieUiModel = SeatSelectMovieUiModel.from(movie)
        view.initializeMovieView(movieUiModel)
    }

    override fun initializeSeatTable(
        selectedSeats: SelectedSeats,
        row: Int,
        col: Int,
    ) {
        this.selectedSeats = selectedSeats
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

        selectedSeats.select(seat)
        reservationAmount += seat.amount()
        view.selectSeat(row, col, selectedSeats.isConfirm())
    }

    private fun applyUnselectSeat(
        seat: Seat,
        row: Int,
        col: Int,
    ) {
        selectedSeats.unselect(seat)
        reservationAmount -= seat.amount()
        view.unselectSeat(row, col)
    }

    override fun confirmSeatSelection() {
        view.moveReservationCompleteView(selectedSeats)
    }

    override fun updateSelectedSeats(selectedSeats: SelectedSeats) {
        this.selectedSeats = selectedSeats
        selectedSeats.seats.forEach { seat ->
            reservationAmount += seat.amount()
            view.selectSeat(seat.row - 1, seat.col - 1, selectedSeats.isConfirm())
            view.updateReservationAmount(reservationAmount.amount)
        }
    }
}
