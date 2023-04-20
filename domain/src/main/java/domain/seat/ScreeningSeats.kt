package domain.seat

class ScreeningSeats(
    values: Map<ScreeningSeat, SeatState> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                ScreeningSeat(row, column)
            }
        }.associateWith {
            SeatState.AVAILABLE
        }
) {

    private val _values: MutableMap<ScreeningSeat, SeatState> = values.toMutableMap()

    val values: Map<ScreeningSeat, SeatState>
        get() = _values.toMap()

    fun selectSeat(seat: ScreeningSeat): ScreeningSeat? {
        val seatState = _values[seat] ?: throw IllegalArgumentException("사용할 수 없는 좌석입니다.")

        if (seatState == SeatState.RESERVED) {
            return null
        }
        _values[seat] = SeatState.RESERVED
        return seat
    }

    fun cancelSeat(seat: ScreeningSeat): ScreeningSeat? {
        val seatState = _values[seat] ?: throw IllegalArgumentException("사용할 수 없는 좌석입니다.")

        if (seatState == SeatState.AVAILABLE) {
            return null
        }
        _values[seat] = SeatState.AVAILABLE
        return seat
    }
}
