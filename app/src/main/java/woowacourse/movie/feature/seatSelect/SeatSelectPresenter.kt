package woowacourse.movie.feature.seatSelect

import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatToggleResult
import woowacourse.movie.model.ticket.seat.Seats
import woowacourse.movie.model.ticket.seat.grade.RowBasedSeatGradePolicy

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val ticketData: TicketData,
) : SeatSelectContract.Presenter {
    val selectedSeats: Seats = Seats(RowBasedSeatGradePolicy())

    override fun initSelectSeatView() {
        view.setMovieTitle(ticketData.screeningData.title)
        view.setTicketPrice(selectedSeats.totalTicketPrice.value)
    }

    override fun onSeatInput(seat: Seat) {
        if (isMaximumSelectedSeat() && !selectedSeats.isSelectedSeat(seat)) {
            view.printError(SeatSelectErrorType.OverBooking)
            return
        }
        toggleSeat(seat)
        view.setSubmitButtonView(isMaximumSelectedSeat())
    }

    private fun isMaximumSelectedSeat(): Boolean = selectedSeats.size() == ticketData.ticketCount

    private fun toggleSeat(seat: Seat) {
        val toggleResult = selectedSeats.toggleSeat(seat)
        when (toggleResult) {
            is SeatToggleResult.Added -> view.seatSelect(seat)
            is SeatToggleResult.Removed -> view.seatUnSelect(seat)
        }
        view.setTicketPrice(selectedSeats.totalTicketPrice.value)
    }

    override fun setSeatsData(seatsData: SeatsData) {
        selectedSeats.updateSeats(seatsData.toSeatList())
        selectedSeats.seats.forEach { view.seatSelect(it) }
        view.setTicketPrice(selectedSeats.totalTicketPrice.value)
        view.setSubmitButtonView(isMaximumSelectedSeat())
    }

    override fun getSelectedSeatsData(): SeatsData = SeatsData.fromSeats(selectedSeats)

    override fun handleCompleteSelectSeat() {
        view.navigateToTicketView(
            ticketData.seatSelectedTicketData(
                SeatsData.fromSeats(selectedSeats),
                selectedSeats.totalTicketPrice.value,
            ),
        )
    }
}
