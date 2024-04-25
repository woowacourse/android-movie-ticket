package woowacourse.movie.model

import java.io.Serializable

class Seats : Serializable {
    var seats = mutableListOf<Seat>()
        private set

    fun manageSelected(
        isSelected: Boolean,
        seat: Seat,
    ) {
        if (isSelected) seats.add(seat) else seats.remove(seat)
    }
}
