package woowacourse.movie.model

import domain.movie.MovieName
import domain.reservation.SeatSelection
import domain.reservation.TicketCount
import domain.seat.ScreeningSeat
import domain.seat.ScreeningSeats
import domain.seat.SeatColumn
import domain.seat.SeatRow
import domain.seat.SeatState
import java.io.Serializable
import java.time.LocalDateTime

data class SeatSelectionInfo(
    val movieName: String,
    val screeningTime: LocalDateTime,
    val seatCount: Int,
    val screeningSeats: Map<Pair<SeatRow, SeatColumn>, SeatState> = SeatRow
        .values()
        .flatMap { row ->
            SeatColumn.values().map { column ->
                row to column
            }
        }.associateWith {
            SeatState.AVAILABLE
        }
) : Serializable

fun SeatSelectionInfo.toDomainModel() = SeatSelection(
    movieName = MovieName(movieName),
    screeningDateTime = screeningTime,
    seatCount = TicketCount(seatCount),
    screeningSeats = ScreeningSeats(
        screeningSeats.map {
            Pair(ScreeningSeat.valueOf(it.key.first, it.key.second), it.value)
        }.toMap()
    )
)
