package woowacourse.movie.model

class Seats {
    var seats = mutableListOf<Seat>()
        private set

    fun manageSelected(
        isSelected: Boolean,
        seat: Seat,
    ) {
        if (isSelected) seats.add(seat) else seats.remove(seat)
    }
}
