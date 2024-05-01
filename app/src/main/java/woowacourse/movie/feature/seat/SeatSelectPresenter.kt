package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.TicketRepository
import woowacourse.movie.model.reservation.ReservationAmount
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatTable
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieRepository: MovieRepository,
    private val ticketRepository: TicketRepository,
) :
    SeatSelectContract.Presenter {
    private lateinit var seatTable: SeatTable
    private lateinit var selectedSeats: SelectedSeats
    private var reservationAmount = ReservationAmount()

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        val movieUiModel = SeatSelectMovieUiModel.from(movie)
        view.initializeMovieView(movieUiModel)
    }

    override fun loadSeatTable(
        selectedSeats: SelectedSeats,
        row: Int,
        col: Int,
    ) {
        this.selectedSeats = selectedSeats
        seatTable = SeatTable(row, col)
        val seatsUiModel = SeatSelectTableUiModel.from(seatTable)
        view.initializeSeatViews(seatsUiModel)
    }

    override fun selectSeat(
        row: Int,
        col: Int,
    ) {
        val seat = seatTable.table[row][col]
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
        runCatching {
            reservationAmount -= seat.amount()
        }.getOrElse {
            view.handleError(SeatSelectError.NegativeReservationAmount)
            return
        }
        view.unselectSeat(row, col)
    }

    override fun finishSeatSelection(
        movieId: Long,
        screeningDateTime: LocalDateTime,
    ) {
        val ticket =
            Ticket(
                movieId = movieId,
                screeningDateTime = screeningDateTime,
                selectedSeats = selectedSeats,
            )
        val ticketId = ticketRepository.save(ticket)
        view.moveReservationCompleteView(ticketId)
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
