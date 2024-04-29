package woowacourse.movie.model.seats

import java.io.Serializable

class Seats : Serializable {
    var seats = mutableListOf<Seat>()
        private set

    var seatsIndex = mutableListOf<Int>()
        private set

    fun restoreSeats(recordOfSeats: Seats): Seats {
        seats = recordOfSeats.seats
        return this
    }

    fun restoreSeatsIndex(seats: List<Int>) {
        seatsIndex = seats.toMutableList()
    }

    fun manageSelectedIndex(
        isSelected: Boolean,
        index: Int,
    ) {
        if (isSelected) seatsIndex.add(index) else seatsIndex.remove(index)
    }

    fun manageSelected(
        isSelected: Boolean,
        seat: Seat,
    ) {
        if (isSelected) seats.add(seat) else seats.remove(seat)
    }
}
