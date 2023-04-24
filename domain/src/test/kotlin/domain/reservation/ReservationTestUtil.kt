package domain.reservation

import domain.movie.Movie
import domain.movie.MovieName
import domain.movie.RunningTime
import domain.movie.ScreeningDate
import domain.movie.ScreeningPeriod
import java.time.LocalDate

fun MockMovie() = Movie(
    movieName = MovieName("해리포터"),
    screeningPeriod = ScreeningPeriod(
        ScreeningDate(LocalDate.of(2000, 10, 1)),
        ScreeningDate(LocalDate.of(2050, 10, 30))
    ),
    runningTime = RunningTime(120),
    description = "마법영화"
)
