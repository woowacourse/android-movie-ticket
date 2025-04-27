package woowacourse.movie.presenter.reservation

import woowacourse.movie.contract.reservation.SeatSelectionContract
import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.Ticket

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val ticket: Ticket,
    selectedSeats: Set<Seat>?,
) : SeatSelectionContract.Presenter {
    private val seats: Set<Seat> = Seat.Companion.seats()
    private var selectedSeats = selectedSeats?.toSet() ?: emptySet()
    private val price: Int get() = selectedSeats.sumOf(Seat::price)

    override fun presentSeats() {
        view.setSeats(seats, selectedSeats)
    }

    override fun presentTitle() {
        view.setTitle(ticket.title)
    }

    override fun presentPrice() {
        view.setPrice(price)
    }

    override fun onSeatSelect(seat: Seat) {
        if (seat in selectedSeats) {
            selectedSeats -= seat
        } else {
            if (canSelectSeat()) {
                selectedSeats += seat
            }
        }

        view.setSeatIsSelected(seat, seat in selectedSeats)
        view.setPrice(price)
    }

    override fun tryReservation() {
        view.askFinalReservation()
    }

    override fun confirmReservation() {
        ticket.run {
            view.navigateToTicketScreen(title, count, showtime)
        }
    }

    private fun canSelectSeat(): Boolean = selectedSeats.size < ticket.count

    override fun getSelectedSeats(): Set<Seat> = selectedSeats.toSet()
}
