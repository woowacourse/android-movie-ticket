package domain.seat

class ScreeningSeats(
    values: Map<ScreeningSeat, SeatState> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                ScreeningSeat.valueOf(row, column)
            }
        }.associateWith {
            SeatState.AVAILABLE
        }
) {

    private val _values: MutableMap<ScreeningSeat, SeatState> = values.toMutableMap()
    val values: Map<ScreeningSeat, SeatState>
        get() = _values.toMap()

    fun selectSeat(seat: ScreeningSeat): ScreeningSeat? {
        val seatState = _values[seat]

        if (seatState == SeatState.SELECTED) {
            return null
        }
        _values[seat] = SeatState.SELECTED
        return seat
    }

    fun cancelSeat(seat: ScreeningSeat): ScreeningSeat? {
        val seatState = _values[seat]

        if (seatState == SeatState.AVAILABLE) {
            return null
        }
        _values[seat] = SeatState.AVAILABLE
        return seat
    }
}
