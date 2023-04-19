package domain.movie

import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow

data class Movie(
    val movieName: MovieName,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
    val description: String,
) {

    private val _availableSeats: MutableList<ScreeningSeat> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                ScreeningSeat(row, column)
            }
        }.toMutableList()
    val availableSeats: List<ScreeningSeat>
        get() = _availableSeats.toList()

    fun reserved(screeningSeats: List<ScreeningSeat>) {
        screeningSeats.forEach { reservedSeat ->
            _availableSeats.remove(reservedSeat)
        }
    }
}
