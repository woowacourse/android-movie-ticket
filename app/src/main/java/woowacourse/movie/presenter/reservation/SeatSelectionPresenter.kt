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
    private val completable get() = ticket.count == selectedSeats.size
    private val price: Int get() = selectedSeats.sumOf(Seat::price)

    override fun fetchAvailableSeats() {
        view.setSeats(seats, selectedSeats)
        view.setConfirmEnabled(completable)
    }

    override fun fetchScreeningDetail() {
        view.setTitle(ticket.title)
        view.setPrice(price)
    }

    override fun selectSeat(seat: Seat) {
        if (seat.isSelected) {
            selectedSeats -= seat
        } else {
            if (canSelectSeat) {
                selectedSeats += seat
            }
        }

        view.setSeatIsSelected(seat, seat.isSelected)
        view.setPrice(price)
        view.setConfirmEnabled(completable)
    }

    private val Seat.isSelected get() = this in selectedSeats

    private val canSelectSeat: Boolean get() = selectedSeats.size < ticket.count

    override fun reserve() {
        view.requestConfirm()
    }

    override fun confirmReservation() {
        ticket.run {
            view.navigateToTicketScreen(title, count, showtime, selectedSeats)
        }
    }

    override fun selectedSeats(): Set<Seat> = selectedSeats.toSet()
}
