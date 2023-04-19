package domain.seat

class ScreeningSeats {

    private val _values: MutableList<ScreeningSeat> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                ScreeningSeat(row, column)
            }
        }.toMutableList()
    val values: List<ScreeningSeat>
        get() = _values.toList()

    fun removeReservedSeats(seats: List<ScreeningSeat>) {
        seats.forEach {
            val reservedSeat = it.reserved()

            _values.remove(it)
            _values.add(reservedSeat)
        }
    }
}
