package woowacourse.movie.presentation.seat

import android.os.Bundle
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.ReservationMovieSeats
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.repository.SeatRepository
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.toTicketModel
import woowacourse.movie.presentation.seat.model.MovieSeatModel
import woowacourse.movie.presentation.seat.model.SeatSelectState
import woowacourse.movie.presentation.seat.model.toMovieSeat
import woowacourse.movie.presentation.seat.model.toMovieSeatModel
import java.io.Serializable

class SeatSelectionPresenter(
    private val pendingMovieReservationModel: PendingMovieReservationModel,
    private val view: SeatSelectionContract.View,
    private val seatRepository: SeatRepository,
) : SeatSelectionContract.Presenter {
    private val reservationMovieSeats = ReservationMovieSeats(pendingMovieReservationModel.count)

    override fun loadTicket() {
        view.showTicket(pendingMovieReservationModel = pendingMovieReservationModel)
    }

    override fun loadSeat() {
        view.showSeat(seatRepository.getAvailableSeats())
    }

    override fun selectSeat(
        rowIndex: Int,
        columIndex: Int,
    ) {
        val seat = seatRepository.getAvailableSeat(rowIndex, columIndex)
        if (seat.seatName.isEmpty()) return
        reservationMovieSeats.setSeatSelectType(seat)
        when (reservationMovieSeats.seatSelectState) {
            SeatSelectState.ADD -> {
                reservationMovieSeats.addSeat(seat)
                view.showSelectedSeat(rowIndex, columIndex)
                view.showCurrentResultTicketPriceView(reservationMovieSeats.getTotalSeatPrice())
            }

            SeatSelectState.REMOVE -> {
                reservationMovieSeats.deleteSeat(seat)
                view.showUnSelectedSeat(rowIndex, columIndex)
                view.showCurrentResultTicketPriceView(reservationMovieSeats.getTotalSeatPrice())
            }

            SeatSelectState.PREVENT -> {}
        }
        updateSeatTypeInView()
    }

    override fun getSeats(): List<MovieSeat> {
        return reservationMovieSeats.userSeats
    }

    override fun ticketing() {
        val ticket =
            Ticket(
                title = pendingMovieReservationModel.title,
                movieDate =
                    MovieDate(
                        pendingMovieReservationModel.movieDate.screeningDate,
                        pendingMovieReservationModel.movieDate.screeningTime,
                    ),
                count = pendingMovieReservationModel.count,
                price = reservationMovieSeats.getTotalSeatPrice(),
                seats = reservationMovieSeats.userSeats,
            ).toTicketModel()
        view.moveToTicketDetail(ticket)
    }

    override fun confirmSeatResult() {
        if (reservationMovieSeats.seatSelectState == SeatSelectState.PREVENT) {
            view.showDialog()
        }
    }

    override fun saveInstance(outState: Bundle) {
        val movieModels = reservationMovieSeats.userSeats.map { it.toMovieSeatModel() }
        outState.putSerializable(KEY_NAME_SEATS, movieModels as Serializable)
    }

    override fun initSavedInstance(seats: List<MovieSeatModel>) {
        seatRepository
            .getSeatRowAndColumn(seats.map { it.toMovieSeat() })
            .forEach {
                selectSeat(it.first, it.second)
            }
    }

    private fun updateSeatTypeInView() {
        reservationMovieSeats.updateSeatSelectType()
        when (reservationMovieSeats.seatSelectState) {
            SeatSelectState.ADD, SeatSelectState.REMOVE -> view.offConfirmAvailableView()
            SeatSelectState.PREVENT -> view.onConfirmAvailableView()
        }
    }

    companion object {
        const val KEY_NAME_SEATS = "seats"
        const val KEY_NAME_TICKET = "ticket"
    }
}
