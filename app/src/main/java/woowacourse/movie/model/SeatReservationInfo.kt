package woowacourse.movie.model

import domain.movie.MovieName
import domain.reservation.SeatReservation
import domain.reservation.TicketCount
import domain.seat.ScreeningSeat
import domain.seat.ScreeningSeats
import domain.seat.SeatColumn
import domain.seat.SeatRow
import domain.seat.SeatState
import java.io.Serializable
import java.time.LocalDateTime

data class SeatReservationInfo(
    val movieName: String,
    val screeningTime: LocalDateTime,
    val selectingCount: Int,
    val screeningSeats: Map<Pair<SeatRow, SeatColumn>, SeatState> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                row to column
            }
        }.associateWith {
            SeatState.AVAILABLE
        }
) : Serializable {

    companion object {

        fun ofError() = SeatReservationInfo(
            "",
            LocalDateTime.MIN,
            0,
        )
    }
}

fun SeatReservationInfo.toDomainModel() = SeatReservation(
    movieName = MovieName(movieName),
    screeningTime = screeningTime,
    selectingCount = TicketCount(selectingCount),
    screeningSeats = ScreeningSeats(
        screeningSeats.map {
            Pair(ScreeningSeat(it.key.first, it.key.second), it.value)
        }.toMap()
    )
)
