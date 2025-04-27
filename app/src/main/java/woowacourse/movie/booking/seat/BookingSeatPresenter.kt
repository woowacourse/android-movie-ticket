package woowacourse.movie.booking.seat

import woowacourse.movie.domain.Seat

class BookingSeatPresenter(
    private val view: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private val selectedSeats = mutableSetOf<Seat>()

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
        val totalPrice = calculateTotalPrice()
        view.updateTotalPrice(totalPrice)
        view.setConfirmEnabled(selectedSeats.isNotEmpty())
    }

    private fun calculateTotalPrice(): Int = selectedSeats.sumOf { it.rank.price }

    override fun onConfirmClicked() {
        view.showConfirmDialog()
    }
}
