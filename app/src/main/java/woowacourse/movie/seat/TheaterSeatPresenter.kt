package woowacourse.movie.seat

import woowacourse.movie.model.theater.Seat

class TheaterSeatPresenter(private val view: TheaterSeatContract.View, private val maxSeats: Int) :
    TheaterSeatContract.Presenter {
    private val seats: MutableMap<String, Seat> = mutableMapOf()
    private var selectedCount = 0
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
        if (seat.chosen) {
            seat.chosen = false
            selectedCount--
        } else {
            if (selectedCount < maxSeats) {
                seat.chosen = true
                selectedCount++
            }
        }
        updateSeatBackground(seatId)
        view.updateSeatDisplay(seat)
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
}
