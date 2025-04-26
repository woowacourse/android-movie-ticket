package woowacourse.movie.model

@JvmInline
value class Seats(
    val values: List<Seat>,
) {
    val amount: Int
        get() = values.sumOf { it.grade.price }

    fun toggle(
        seat: Seat,
        headCount: Int,
    ): Seats {
        val isAlreadySelected = values.any { it.seatName == seat.seatName }

        val updatedSeats =
            if (isAlreadySelected) {
                values.filterNot { it.seatName == seat.seatName }
            } else {
                if (values.size >= headCount) return this

                values + seat
            }

        return Seats(updatedSeats)
    }
}
