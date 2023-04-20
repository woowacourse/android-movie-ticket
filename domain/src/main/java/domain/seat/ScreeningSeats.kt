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
        val seatState = this[seat]

        if (seatState == SeatState.SELECTED) {
            return null
        }
        _values[seat] = SeatState.SELECTED
        return seat
    }

    fun cancelSeat(seat: ScreeningSeat): ScreeningSeat? {
        val seatState = this[seat]

        if (seatState == SeatState.AVAILABLE) {
            return null
        }
        _values[seat] = SeatState.AVAILABLE
        return seat
    }

    operator fun get(seat: ScreeningSeat): SeatState {
        return _values[seat] ?: throw IllegalArgumentException(ERROR_NOT_EXIST_SEAT)
    }

    companion object {
        private const val ERROR_NOT_EXIST_SEAT = "사용할 수 없는 좌석입니다."
    }
}
