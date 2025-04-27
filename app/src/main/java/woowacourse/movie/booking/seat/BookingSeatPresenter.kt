package woowacourse.movie.booking.seat

import woowacourse.movie.booking.complete.BookingCompleteUiModel
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Ticket

class BookingSeatPresenter(
    private val view: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private val selectedSeats = mutableListOf<Seat>()
    private lateinit var bookingCompleteUiModel: BookingCompleteUiModel
    private var totalPrice: Int = 0

    override fun loadMovieTitle(ticket: Ticket) {
        view.showMovieTitle(ticket.movieTitle)
    }

    override fun toggleSeatSelection(seat: Seat) {
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat)
            view.updateSeatState(seat, isSelected = false)
        } else {
            selectedSeats.add(seat)
            view.updateSeatState(seat, isSelected = true)
        }
        updateView()
    }

    private fun updateView() {
        calculateTotalPrice()
        view.updateTotalPrice(totalPrice)
        view.setConfirmEnabled(selectedSeats.isNotEmpty())
    }

    private fun calculateTotalPrice() {
        totalPrice = selectedSeats.sumOf { it.rank.price }
    }

    override fun onConfirmClicked() {
        view.showConfirmDialog()
    }

    override fun onConfirmDialogClicked(ticket: Ticket) {
        bookingCompleteUiModel =
            BookingCompleteUiModel(
                title = ticket.movieTitle,
                date = ticket.date.toString(),
                time = ticket.time.toString(),
                seats = selectedSeats.map { it.toString() },
                ticketQuantity = ticket.quantity.value,
                ticketTotalPrice = totalPrice,
            )

        view.navigateToBookingComplete(bookingCompleteUiModel = bookingCompleteUiModel)
    }
}
