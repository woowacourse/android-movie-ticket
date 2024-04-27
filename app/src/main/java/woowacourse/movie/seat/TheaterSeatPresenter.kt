package woowacourse.movie.seat

import woowacourse.movie.model.theater.Seat

class TheaterSeatPresenter(
    private val view: TheaterSeatContract.View,
    val ticketLimit: Int
) :
    TheaterSeatContract.Presenter {
    private val seats: MutableMap<String, Seat> = mutableMapOf()
    private var selectedSeats = mutableListOf<String>()

    init {
        val rows = mapOf(1 to "B", 2 to "B", 3 to "S", 4 to "S", 5 to "A")
        for (row in 1..5) {
            for (col in 1..4) {
                val seatId = "${row.toChar() + (('A' - 1).code)}$col"
                val grade = rows[row] ?: "B"
                seats[seatId] = Seat(row.toChar() + (('A' - 1).code), col, grade)
            }
        }
    }

    override fun toggleSeatSelection(seatId: String) {
        val seat = seats[seatId] ?: return
        if (seatId in selectedSeats) {
            seat.chosen = false
            selectedSeats.remove(seatId)
        } else {
            if (selectedSeats.size < ticketLimit) {
                seat.chosen = true
                selectedSeats.add(seatId)
            }
        }
        updateSeatBackground(seatId)
        calculateAndUpdateTotalPrice()
    }


    override fun updateSeatBackground(seatId: String) {
        val seat = seats[seatId] ?: return
        if (seat.chosen) {
            view.setSeatBackground(seatId, "#FF0000")
        } else {
            view.setSeatBackground(seatId, "#FFFFFF")
        }
    }


    override fun calculateTotalPrice(): Int {
        return seats.values.filter { it.chosen }.sumOf { it.price }
    }

    override fun showConfirmationDialog() {
        if (seats.values.any { it.chosen }) {
            view.showConfirmationDialog()
        }
    }

    override fun getSelectedSeatNumbers(): String {
        return selectedSeats.toString()
    }

    private fun calculateAndUpdateTotalPrice() {
        val totalPrice = selectedSeats.sumOf { seatId ->
            seats[seatId]?.price ?: 0
        }
        view.updateTotalPrice(totalPrice)
    }
}
