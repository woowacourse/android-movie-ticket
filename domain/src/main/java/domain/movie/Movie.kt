package domain.movie

import domain.seat.ScreeningSeats

data class Movie(
    val movieName: MovieName,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
    val description: String,
    val availableSeats: ScreeningSeats = ScreeningSeats(),
)
