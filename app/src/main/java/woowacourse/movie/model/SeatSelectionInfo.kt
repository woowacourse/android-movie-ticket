package woowacourse.movie.model

import domain.movie.MovieName
import domain.reservation.SeatSelection
import domain.reservation.TicketCount
import domain.seat.ScreeningSeat
import domain.seat.ScreeningSeats
import java.io.Serializable
import java.time.LocalDateTime

data class SeatSelectionInfo(
    val movieName: String,
    val screeningTime: LocalDateTime,
    val seatCount: Int,
    val screeningPlace: ScreeningPlace = ScreeningPlace.Default
) : Serializable

fun SeatSelectionInfo.toDomainModel() = SeatSelection(
    movieName = MovieName(movieName),
    screeningDateTime = screeningTime,
    seatCount = TicketCount(seatCount),
    screeningSeats = ScreeningSeats(
        screeningPlace.seats.map {
            ScreeningSeat.valueOf(it.key.row, it.key.column) to it.value
        }.toMap()
    )
)
